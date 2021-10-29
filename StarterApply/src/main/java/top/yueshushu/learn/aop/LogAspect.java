package top.yueshushu.learn.aop;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.core.io.InputStreamSource;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

/**
 * 可以使用，但暂时不用。
 * @date 2021/10/25 10:53
 * @author zk_yjl
 */
@Slf4j
/*@Aspect
@Component
@Order(1)*/
public class LogAspect {

    /**
     * 异常，输出完整的stack trace
     */
    private boolean printFullStackTraceForException = true;

    /**
     * (输入输出)参数最大输出长度. -1表示不限制
     */
    private int paramMaxPrintLength = 20000;

  /*  @Pointcut("(execution(public * top.yueshushu.learn.controller.*.*(..))) " +
            "|| (execution(public * top.yueshushu.learn.controller.*.*(..)))")*/
    public void log(){
    }

  /*  @Before("log()")*/
    public void doBefore(JoinPoint joinPoint) throws Throwable {
    }

  /*  @AfterReturning(value = "log()", returning = "ret")*/
    public void doAfterReturning(Object ret) throws Throwable {
    }

  /*  @Around("log()")*/
    public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable {

        String invokeMethodFullPath = buildInvokeMethodFullPath(joinPoint);
        String requestParams = buildRequestParams(joinPoint);
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        String method = Optional.ofNullable(attributes).map(attr -> attr.getRequest().getMethod()).orElse(null);
        StringBuilder logInfo = new StringBuilder();
        logInfo.append("request method: ").append(invokeMethodFullPath).append("; ");
        logInfo.append("request type: ").append(method).append("; ");
        logInfo.append("request param: ").append(requestParams).append("; ");

        long startMs =  System.currentTimeMillis();

        try {
            Object result = joinPoint.proceed();

            long cost = System.currentTimeMillis() - startMs;
            logInfo.insert(0, "cost(ms): " + cost + "; ");
            logInfo.append("    -----    response: ").append(toJsonString(result));

            log.info(logInfo.toString());
            return result;
        } catch (Throwable throwable) {

            long cost = System.currentTimeMillis() - startMs;
            logInfo.insert(0, "cost(ms): " + cost + "; ");

            if (printFullStackTraceForException) {
                log.error("error. " + logInfo.toString(), throwable);
            } else {
                log.error("error. " + throwable.getMessage() + "; " + logInfo.toString());
            }
            throw throwable;
        }
    }

    private String toJsonString(Object result) {

        String json = JSON.toJSONString(result);
        if (paramMaxPrintLength <= 0) {
            return json;
        }

        if (json.length() > paramMaxPrintLength) {
            return json.substring(0, paramMaxPrintLength) + "...";
        }

        return json;
    }

    private String buildRequestParams(ProceedingJoinPoint point) {
        try {
            Map<String, Object> requestP = new LinkedHashMap<>();
            Method m = ((MethodSignature) point.getSignature()).getMethod();
            Parameter[] parameters = m.getParameters();
            for (int i = 0, iLen = parameters.length; i < iLen; i++) {
                //过滤Request、Response or InputStreamSource对象，防止序列化异常
                Object arg = point.getArgs()[i];
                if (null == arg) {
                    continue;
                }

                if (arg instanceof HttpServletRequest
                        || arg instanceof HttpServletResponse
                        || arg instanceof InputStreamSource
                        || arg instanceof Errors)  {
                    continue;
                }
                requestP.put(parameters[i].getName(), arg);
            }

            // 提前构造入参信息，防方法内修改入参对象，异常时再构造入参会不准
            return toJsonString(requestP);
        } catch (Exception e) {
            log.warn("请求参数构造失败. error msg: " + e.getMessage());
            return "build error";
        }
    }


    private String buildInvokeMethodFullPath(ProceedingJoinPoint point) {

        Signature signature = point.getSignature();
        Class<?> targetClass = point.getTarget().getClass();
        // 执行方法的路径
        return targetClass.getSimpleName() + " " + signature.getName();
    }

}
