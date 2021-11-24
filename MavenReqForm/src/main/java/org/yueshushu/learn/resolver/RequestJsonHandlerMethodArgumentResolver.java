package org.yueshushu.learn.resolver;

/**
 * @ClassName:ReqResolver
 * @Description TODO
 * @Author zk_yjl
 * @Date 2021/11/24 8:58
 * @Version 1.0
 * @Since 1.0
 **/
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.config.BeanExpressionContext;
import org.springframework.beans.factory.config.BeanExpressionResolver;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.core.MethodParameter;
import org.springframework.core.ResolvableType;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.util.Assert;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindException;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ValueConstants;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.RequestScope;
import org.springframework.web.method.annotation.ModelFactory;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.mvc.method.annotation.ExtendedServletRequestDataBinder;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.web.util.WebUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.yueshushu.learn.annotation.RequestJson;

public class RequestJsonHandlerMethodArgumentResolver implements HandlerMethodArgumentResolver {

    private static final String JSONBODY_ATTRIBUTE = "JSON_REQUEST_BODY";
    private static final TypeDescriptor STRING_TYPE_DESCRIPTOR = TypeDescriptor.valueOf(String.class);
    private final boolean annotationNotRequired;
    private final ConfigurableBeanFactory configurableBeanFactory;

    private final BeanExpressionContext expressionContext;
    private final boolean useDefaultResolution;

    private Map<MethodParameter, NamedValueInfo> namedValueInfoCache = new ConcurrentHashMap<MethodParameter, NamedValueInfo>(256);


    public RequestJsonHandlerMethodArgumentResolver(ConfigurableBeanFactory beanFactory) {
        this.configurableBeanFactory = beanFactory;
        this.expressionContext = (beanFactory != null ? new BeanExpressionContext(beanFactory, new RequestScope()) : null);
        this.annotationNotRequired=true;
        this.useDefaultResolution = false;
    }

    public RequestJsonHandlerMethodArgumentResolver() {
        this.annotationNotRequired=true;
        this.useDefaultResolution = false;
        this.configurableBeanFactory = null;
        this.expressionContext = null;
    }

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        Class<?> paramType = parameter.getParameterType();
        if (parameter.hasParameterAnnotation(RequestJson.class)) {
            return true;
        }  else {
            if (parameter.hasParameterAnnotation(RequestPart.class)) {
                return false;
            }
            else if (MultipartFile.class.equals(paramType) || "javax.servlet.http.Part".equals(paramType.getName())) {
                return true;
            } else{
                return this.useDefaultResolution && BeanUtils.isSimpleProperty(paramType);
            }

        }

    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        HttpServletRequest servletRequest = webRequest.getNativeRequest(HttpServletRequest.class);
        if("application/json".equals(servletRequest.getContentType())||"application/json; charset=utf-8".equals(servletRequest.getContentType())) {
            //如果是json
            return bindRequestParameters( parameter, webRequest);
        } else {
            //表单
            Class<?> parameterType = parameter.getParameterType();
            if (BeanUtils.isSimpleProperty(parameterType)) {
                //如果是Map对象，调用resolveMapArgument方法
                if (Map.class.isAssignableFrom(parameter.getParameterType())) {
                    return resolveMapArgument(parameter,mavContainer,webRequest,binderFactory);
                }
                Class<?> paramType = parameter.getParameterType();
                NamedValueInfo namedValueInfo = getNamedValueInfo(parameter);

                Object arg = resolveName(namedValueInfo.name, parameter, webRequest);
                if (arg == null) {
                    if (namedValueInfo.defaultValue != null) {
                        arg = resolveDefaultValue(namedValueInfo.defaultValue);
                    }
                    else if (namedValueInfo.required && !parameter.getParameterType().getName().equals("java.util.Optional")) {
                        handleMissingValue(namedValueInfo.name, parameter);
                    }
                    arg = handleNullValue(namedValueInfo.name, arg, paramType);
                }
                else if ("".equals(arg) && namedValueInfo.defaultValue != null) {
                    arg = resolveDefaultValue(namedValueInfo.defaultValue);
                }

                if (binderFactory != null) {
                    WebDataBinder binder = binderFactory.createBinder(webRequest, null, namedValueInfo.name);
                    arg = binder.convertIfNecessary(arg, paramType, parameter);
                }

                handleResolvedValue(arg, namedValueInfo.name, parameter, mavContainer, webRequest);

                return arg;
            } else {
                String name = ModelFactory.getNameForParameter(parameter);
                Object attribute = (mavContainer.containsAttribute(name) ?
                        mavContainer.getModel().get(name) : createAttribute(name, parameter, binderFactory, webRequest));

                WebDataBinder binder = binderFactory.createBinder(webRequest, attribute, name);
                if (binder.getTarget() != null) {
                    bindRequestParameters(binder, webRequest);
                    validateIfApplicable(binder, parameter);
                    if (binder.getBindingResult().hasErrors() && isBindExceptionRequired(binder, parameter)) {
                        throw new BindException(binder.getBindingResult());
                    }
                }

                // 在模型的最后添加解析属性和BindingResult
                Map<String, Object> bindingResultModel = binder.getBindingResult().getModel();
                mavContainer.removeAttributes(bindingResultModel);
                mavContainer.addAllAttributes(bindingResultModel);
                return binder.convertIfNecessary(binder.getTarget(), parameter.getParameterType(), parameter);
            }
        }
    }


    /**
     * 解析json
     */
    private Object bindRequestParameters( MethodParameter parameter, NativeWebRequest webRequest) throws Exception {
        HttpServletRequest servletRequest = webRequest.getNativeRequest(HttpServletRequest.class);
        // 有就直接获取
        String jsonBody = (String) webRequest.getAttribute(JSONBODY_ATTRIBUTE, NativeWebRequest.SCOPE_REQUEST);
        // 没有就从请求中读取
        if (jsonBody == null) {
            try {
                jsonBody = IOUtils.toString(servletRequest.getReader());
                webRequest.setAttribute(JSONBODY_ATTRIBUTE, jsonBody, NativeWebRequest.SCOPE_REQUEST);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        JSONObject jsonObject = JSON.parseObject(jsonBody);
        // 根据@RequestJson注解value作为json解析的key
        RequestJson parameterAnnotation = parameter.getParameterAnnotation(RequestJson.class);
        //注解的value是JSON的key
        String key = parameterAnnotation.value();
        Object value = null;
        // 如果@RequestJson注解没有设置value，则取参数名FrameworkServlet作为json解析的key
        if (!StringUtils.isEmpty(key)) {
            value = jsonObject.get(key);
            // 如果设置了value但是解析不到，报错
            if (value == null) {
                if(parameterAnnotation.required()) {
                    throw new IllegalArgumentException(String.format("required param %s is not present", key));
                }else {
                    //如果是基本类型，直接给默认值，如果不是，继续做解析
                    Class<?> parameterType = parameter.getParameterType();
                    if (isBasicDataTypes(parameterType)||isBaseType(parameterType)) {
                        value=parameterAnnotation.defaultValue();
                    }
                }
            }
        } else {
            // 注解为设置value则用参数名当做json的key
            key = parameter.getParameterName();
            if(jsonObject!=null) {
                value = jsonObject.get(key);
            }
            if (value == null) {
                if(parameterAnnotation.required()) {
                    throw new IllegalArgumentException(String.format("required param %s is not present", key));
                }else {
                    //如果是基本类型，直接给默认值，如果不是，继续做解析
                    Class<?> parameterType = parameter.getParameterType();
                    if (isBasicDataTypes(parameterType)||isBaseType(parameterType)) {
                        value=parameterAnnotation.defaultValue();
                    }
                }
            }
        }

        Class<?> parameterType = parameter.getParameterType();
        // 通过注解的value或者参数名解析，能拿到value进行解析
        if (value != null) {
            if (isBasicDataTypes(parameterType)) {
                return value;
            }
            return JSON.parseObject(value.toString(), parameterType);
        }

        // 解析不到则将整个json串解析为当前参数类型
        if (isBasicDataTypes(parameterType)) {
            if (parameterAnnotation.required()) {
                throw new IllegalArgumentException(String.format("required param %s is not present", key));
            } else {
                return null;
            }
        }

        Object result = parameterType.newInstance();
        // 非基本类型，不允许解析所有字段，返回null
        if (!parameterAnnotation.parseAllFields()) {
            // 如果是必传参数抛异常
            if (parameterAnnotation.required()) {
                throw new IllegalArgumentException(String.format("required param %s is not present", key));
            }
            // 否则返回空对象
            return result;
        }
        // 非基本类型，允许解析，将外层属性解析
        result = JSON.parseObject(jsonObject.toString(), parameterType);
        // 如果非必要参数直接返回，否则如果没有一个属性有值则报错
        if (!parameterAnnotation.required()) {
            return result;
        }else{
            boolean haveValue = false;
            Field[] declaredFields = parameterType.getDeclaredFields();
            for(Field field : declaredFields){
                field.setAccessible(true);
                if(field.get(result) != null){
                    haveValue = true;
                    break;
                }
            }
            if(!haveValue){
                throw new IllegalArgumentException(String.format("required param %s is not present", key));
            }
            return result;
        }

    }

    @SuppressWarnings("rawtypes")
    private boolean isBaseType(Class clazz) {
        String name =clazz.getName();
        Set<String> baseTypes = new HashSet<>();
        baseTypes.add("int");
        baseTypes.add("long");
        baseTypes.add("short");
        baseTypes.add("float");
        baseTypes.add("double");
        baseTypes.add("boolean");
        baseTypes.add("char");
        if(baseTypes.contains(name)) {
            return true;
        }else {
            return false;
        }
    }
    @SuppressWarnings("rawtypes")
    private boolean isBasicDataTypes(Class clazz) {
        Set<Class> classSet = new HashSet<>();
        classSet.add(String.class);
        classSet.add(Integer.class);
        classSet.add(Long.class);
        classSet.add(Short.class);
        classSet.add(Float.class);
        classSet.add(Double.class);
        classSet.add(Boolean.class);
        classSet.add(Character.class);
        return classSet.contains(clazz);
    }

    /**
     * 解析表单
     */
    public Object resolveMapArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
                                     NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {

        Class<?> paramType = parameter.getParameterType();

        Map<String, String[]> parameterMap = webRequest.getParameterMap();
        if (MultiValueMap.class.isAssignableFrom(paramType)) {
            MultiValueMap<String, String> result = new LinkedMultiValueMap<String, String>(parameterMap.size());
            for (Map.Entry<String, String[]> entry : parameterMap.entrySet()) {
                for (String value : entry.getValue()) {
                    result.add(entry.getKey(), value);
                }
            }
            return result;
        }
        else {
            Map<String, String> result = new LinkedHashMap<String, String>(parameterMap.size());
            for (Map.Entry<String, String[]> entry : parameterMap.entrySet()) {
                if (entry.getValue().length > 0) {
                    result.put(entry.getKey(), entry.getValue()[0]);
                }
            }
            return result;
        }
    }
    /**
     * 获取给定方法参数的命名值
     */
    private NamedValueInfo getNamedValueInfo(MethodParameter parameter) {
        NamedValueInfo namedValueInfo = this.namedValueInfoCache.get(parameter);
        if (namedValueInfo == null) {
            namedValueInfo = createNamedValueInfo(parameter);
            namedValueInfo = updateNamedValueInfo(parameter, namedValueInfo);
            this.namedValueInfoCache.put(parameter, namedValueInfo);
        }
        return namedValueInfo;
    }
    protected NamedValueInfo createNamedValueInfo(MethodParameter parameter) {
        RequestJson ann = parameter.getParameterAnnotation(RequestJson.class);
        return (ann != null ? new RequestCustomParamNamedValueInfo(ann) : new RequestCustomParamNamedValueInfo());
    }

    protected Object resolveName(String name, MethodParameter parameter, NativeWebRequest webRequest) throws Exception {
        HttpServletRequest servletRequest = webRequest.getNativeRequest(HttpServletRequest.class);
        MultipartHttpServletRequest multipartRequest =
                WebUtils.getNativeRequest(servletRequest, MultipartHttpServletRequest.class);
        Object arg;

        if (MultipartFile.class.equals(parameter.getParameterType())) {
            assertIsMultipartRequest(servletRequest);
            Assert.notNull(multipartRequest, "Expected MultipartHttpServletRequest: is a MultipartResolver configured?");
            arg = multipartRequest.getFile(name);
        }
        else if (isMultipartFileCollection(parameter)) {
            assertIsMultipartRequest(servletRequest);
            Assert.notNull(multipartRequest, "Expected MultipartHttpServletRequest: is a MultipartResolver configured?");
            arg = multipartRequest.getFiles(name);
        }
        else if (isMultipartFileArray(parameter)) {
            assertIsMultipartRequest(servletRequest);
            Assert.notNull(multipartRequest, "Expected MultipartHttpServletRequest: is a MultipartResolver configured?");
            List<MultipartFile> multipartFiles = multipartRequest.getFiles(name);
            arg = multipartFiles.toArray(new MultipartFile[multipartFiles.size()]);
        }
        else if ("javax.servlet.http.Part".equals(parameter.getParameterType().getName())) {
            assertIsMultipartRequest(servletRequest);
            arg = servletRequest.getPart(name);
        }
        else if (isPartCollection(parameter)) {
            assertIsMultipartRequest(servletRequest);
            arg = new ArrayList<Object>(servletRequest.getParts());
        }
        else if (isPartArray(parameter)) {
            assertIsMultipartRequest(servletRequest);
            arg = RequestPartResolver.resolvePart(servletRequest);
        }
        else {
            arg = null;
            if (multipartRequest != null) {
                List<MultipartFile> files = multipartRequest.getFiles(name);
                if (!files.isEmpty()) {
                    arg = (files.size() == 1 ? files.get(0) : files);
                }
            }
            if (arg == null) {
                String[] paramValues = webRequest.getParameterValues(name);
                if (paramValues != null) {
                    arg = paramValues.length == 1 ? paramValues[0] : paramValues;
                }
            }
        }
        return arg;
    }

    private void assertIsMultipartRequest(HttpServletRequest request) {
        String contentType = request.getContentType();
        if (contentType == null || !contentType.toLowerCase().startsWith("multipart/")) {
            throw new MultipartException("The current request is not a multipart request");
        }
    }

    private boolean isMultipartFileCollection(MethodParameter parameter) {
        Class<?> collectionType = getCollectionParameterType(parameter);
        return ((collectionType != null) && collectionType.equals(MultipartFile.class));
    }

    private boolean isPartCollection(MethodParameter parameter) {
        Class<?> collectionType = getCollectionParameterType(parameter);
        return ((collectionType != null) && "javax.servlet.http.Part".equals(collectionType.getName()));
    }

    private boolean isPartArray(MethodParameter parameter) {
        Class<?> paramType = parameter.getParameterType().getComponentType();
        return ((paramType != null) && "javax.servlet.http.Part".equals(paramType.getName()));
    }

    private boolean isMultipartFileArray(MethodParameter parameter) {
        Class<?> paramType = parameter.getParameterType().getComponentType();
        return ((paramType != null) && MultipartFile.class.equals(paramType));
    }

    private Class<?> getCollectionParameterType(MethodParameter parameter) {
        Class<?> paramType = parameter.getParameterType();
        if (Collection.class.equals(paramType) || List.class.isAssignableFrom(paramType)){
            Class<?> valueType =ResolvableType.forMethodParameter(parameter).asCollection().resolveGeneric();
            if (valueType != null) {
                return valueType;
            }
        }
        return null;
    }

    protected void handleMissingValue(String name, MethodParameter parameter) throws ServletException {
        throw new MissingServletRequestParameterException(name, parameter.getParameterType().getSimpleName());
    }


    public void contributeMethodArgument(MethodParameter parameter, Object value,
                                         UriComponentsBuilder builder, Map<String, Object> uriVariables, ConversionService conversionService) {


        Class<?> paramType = parameter.getParameterType();
        if (Map.class.isAssignableFrom(paramType) || MultipartFile.class.equals(paramType) ||
                "javax.servlet.http.Part".equals(paramType.getName())) {
            return;
        }

        RequestJson ann = parameter.getParameterAnnotation(RequestJson.class);
        String name = (ann == null || !StringUtils.hasText(ann.value()) ? parameter.getParameterName() : ann.value());

        if (value == null) {
            builder.queryParam(name);
        }
        else if (value instanceof Collection) {
            for (Object element : (Collection<?>) value) {
                element = formatUriValue(conversionService, TypeDescriptor.nested(parameter, 1), element);
                builder.queryParam(name, element);
            }
        }
        else {
            builder.queryParam(name, formatUriValue(conversionService, new TypeDescriptor(parameter), value));
        }
    }

    protected String formatUriValue(ConversionService cs, TypeDescriptor sourceType, Object value) {
        if (value == null) {
            return null;
        }
        else if (value instanceof String) {
            return (String) value;
        }
        else if (cs != null) {
            return (String) cs.convert(value, sourceType, STRING_TYPE_DESCRIPTOR);
        }
        else {
            return value.toString();
        }
    }

    /**
     * 根据给定的NamedValueInfo创建一个新的NamedValueInfo，该NamedValueInfo具有经过解析的值
     */
    private NamedValueInfo updateNamedValueInfo(MethodParameter parameter, NamedValueInfo info) {
        String name = info.name;
        if (info.name.length() == 0) {
            name = parameter.getParameterName();
            if (name == null) {
                throw new IllegalArgumentException("Name for argument type [" + parameter.getParameterType().getName() +
                        "] not available, and parameter name information not found in class file either.");
            }
        }
        String defaultValue = (ValueConstants.DEFAULT_NONE.equals(info.defaultValue) ? null : info.defaultValue);
        return new NamedValueInfo(name, info.required, defaultValue);
    }



    /**
     * 将给定的默认值解析为参数值
     */
    private Object resolveDefaultValue(String defaultValue) {
        if (this.configurableBeanFactory == null) {
            return defaultValue;
        }
        String placeholdersResolved = this.configurableBeanFactory.resolveEmbeddedValue(defaultValue);
        BeanExpressionResolver exprResolver = this.configurableBeanFactory.getBeanExpressionResolver();
        if (exprResolver == null) {
            return defaultValue;
        }
        return exprResolver.evaluate(placeholdersResolved, this.expressionContext);
    }



    /**
     * A {@code null} results in a {@code false} value for {@code boolean}s or an exception for other primitives.
     */
    private Object handleNullValue(String name, Object value, Class<?> paramType) {
        if (value == null) {
            if (Boolean.TYPE.equals(paramType)) {
                return Boolean.FALSE;
            }
            else if (paramType.isPrimitive()) {
                throw new IllegalStateException("Optional " + paramType + " parameter '" + name +
                        "' is present but cannot be translated into a null value due to being declared as a " +
                        "primitive type. Consider declaring it as object wrapper for the corresponding primitive type.");
            }
        }
        return value;
    }

    /**
     * Invoked after a value is resolved.
     * @param arg the resolved argument value
     * @param name the argument name
     * @param parameter the argument parameter type
     * @param mavContainer the {@link ModelAndViewContainer}, which may be {@code null}
     * @param webRequest the current request
     */
    protected void handleResolvedValue(Object arg, String name, MethodParameter parameter,
                                       ModelAndViewContainer mavContainer, NativeWebRequest webRequest) {
    }

    /**
     * Extension point to create the model attribute if not found in the model.
     * The default implementation uses the default constructor.
     *
     * @param attributeName the name of the attribute (never {@code null})
     * @param methodParam   the method parameter
     * @param binderFactory for creating WebDataBinder instance
     * @param request       the current request
     * @return the created model attribute (never {@code null})
     */
    protected Object createAttribute(String attributeName, MethodParameter methodParam,
                                     WebDataBinderFactory binderFactory, NativeWebRequest request) throws Exception {

        return BeanUtils.instantiateClass(methodParam.getParameterType());
    }

    /**
     * Extension point to bind the request to the target object.
     *
     * @param binder  the data binder instance to use for the binding
     * @param request the current request
     */
    protected void bindRequestParameters(WebDataBinder binder, NativeWebRequest request) {
        HttpServletRequest servletRequest = request.getNativeRequest(HttpServletRequest.class);
        ((ExtendedServletRequestDataBinder) binder).bind(servletRequest);
    }


    /**
     * Validate the model attribute if applicable.
     * <p>The default implementation checks for {@code @javax.validation.Valid},
     * Spring's {@link org.springframework.validation.annotation.Validated},
     * and custom annotations whose name starts with "Valid".
     *
     * @param binder      the DataBinder to be used
     * @param methodParam the method parameter
     */
    protected void validateIfApplicable(WebDataBinder binder, MethodParameter methodParam) {
        Annotation[] annotations = methodParam.getParameterAnnotations();
        for (Annotation ann : annotations) {
            Validated validatedAnn = AnnotationUtils.getAnnotation(ann, Validated.class);
            if (validatedAnn != null || ann.annotationType().getSimpleName().startsWith("Valid")) {
                Object hints = (validatedAnn != null ? validatedAnn.value() : AnnotationUtils.getValue(ann));
                Object[] validationHints = (hints instanceof Object[] ? (Object[]) hints : new Object[]{hints});
                binder.validate(validationHints);
                break;
            }
        }
    }

    /**
     * Whether to raise a fatal bind exception on validation errors.
     *
     * @param binder      the data binder used to perform data binding
     * @param methodParam the method argument
     * @return {@code true} if the next method argument is not of type {@link Errors}
     */
    protected boolean isBindExceptionRequired(WebDataBinder binder, MethodParameter methodParam) {
        int i = methodParam.getParameterIndex();
        Class<?>[] paramTypes = methodParam.getMethod().getParameterTypes();
        boolean hasBindingResult = (paramTypes.length > (i + 1) && Errors.class.isAssignableFrom(paramTypes[i + 1]));
        return !hasBindingResult;
    }

    public boolean supportsReturnType(MethodParameter returnType) {
        if (returnType.getMethodAnnotation(RequestJson.class) != null) {
            return true;
        } else
            return this.annotationNotRequired && !BeanUtils.isSimpleProperty(returnType.getParameterType());
    }

    public void handleReturnValue(Object returnValue, MethodParameter returnType,
                                  ModelAndViewContainer mavContainer, NativeWebRequest webRequest) throws Exception {

        if (returnValue != null) {
            String name = ModelFactory.getNameForReturnValue(returnValue, returnType);
            mavContainer.addAttribute(name, returnValue);
        }
    }



    private static class RequestCustomParamNamedValueInfo extends NamedValueInfo {

        public RequestCustomParamNamedValueInfo() {
            super("", false, ValueConstants.DEFAULT_NONE);
        }

        public RequestCustomParamNamedValueInfo(RequestJson annotation) {
            super(annotation.value(), annotation.required(), annotation.defaultValue());
        }
    }

    private static class RequestPartResolver {

        public static Object resolvePart(HttpServletRequest servletRequest) throws Exception {
            return servletRequest.getParts().toArray(new Part[servletRequest.getParts().size()]);
        }
    }
    /**
     * Represents the information about a named value, including name, whether it's required and a default value.
     */
    protected static class NamedValueInfo {

        private final String name;

        private final boolean required;

        private final String defaultValue;

        public NamedValueInfo(String name, boolean required, String defaultValue) {
            this.name = name;
            this.required = required;
            this.defaultValue = defaultValue;
        }
    }

}