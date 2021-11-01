package top.yueshushu.log;

import com.alibaba.fastjson.JSONObject;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

@Aspect
/**
 * 自定义日志输出AOP切面，定义以添加了MyLog注解的所有方法作为连接点，
 * 这些连接点触发时定义对应正常方法返回时通知以及异常发生时通知
 */
public class LogAspect{
    private LogService logService;
    private MyLogProperties mylogProperties;

    public LogAspect(MyLogProperties myLogProperties,LogService logService){
        this.logService=logService;
        this.mylogProperties=myLogProperties;
    }
    /**
     * 切点连接点：在MyLog注解的位置切入
     */
    @Pointcut(value ="(@annotation(top.yueshushu.log.MyLog)) ||(execution(public * *..controller.*.*(..))))")
    public void doMyLogCut() {

    }
    /**
     * MyLog注解方法执行 Around 触发事件
     * @param joinPoint
     * @param
     */
    @Around(value = "doMyLogCut()")
    public Object logInvoke(ProceedingJoinPoint joinPoint) throws Throwable{
        //记录一下时间，
        long beginTime = System.currentTimeMillis();
        Object keys = joinPoint.proceed();
        long time = System.currentTimeMillis() - beginTime;
        LogVo myLogVO = this.getMyLog(joinPoint, keys,null);
        myLogVO.setExecTime(time);
        /**
          运行的时间长 才执行操作
         */
        if(mylogProperties.getRunTime()<=time){
            logService.logHandler(myLogVO);
        }
        return keys;
    }
    /**
     * 异常发生时的通知
     * @param joinPoint
     * @param e
     */
    @AfterThrowing(pointcut = "doMyLogCut()", throwing = "e")
    public void doExceptionMyLog(JoinPoint joinPoint, Throwable e) {
        LogVo myLogVO = this.getMyLog(joinPoint, null,e);
        //出现异常，执行时间为 -1
        myLogVO.setExecTime(-1L);
        // 异常的，一直都进行操作.
        logService.logHandler(myLogVO);
    }
    /**
     * 获取输出日志实体
     * @param joinPoint 触发的连接点
     * @param e 异常对象
     * @return MyLogVO
     */
    private LogVo getMyLog(JoinPoint joinPoint,Object keys,Throwable e){
       // 获取RequestAttributes
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        // 从获取RequestAttributes中获取HttpServletRequest的信息
        HttpServletRequest request = (HttpServletRequest) requestAttributes
                .resolveReference(RequestAttributes.REFERENCE_REQUEST);
        // 输出日志VO
        LogVo myLogVO = new LogVo();
        try {
            // 从切面织入点处通过反射机制获取织入点处的方法
            MethodSignature signature = (MethodSignature) joinPoint.getSignature();
            // 获取切入点所在的方法
            Method method = signature.getMethod();
            // 获取操作
            MyLog opLog = method.getAnnotation(MyLog.class);
            if (opLog != null) {
                myLogVO.setModel(opLog.module());
                myLogVO.setOptType(opLog.optType());
                myLogVO.setDescription(opLog.description());
            }
            // 获取请求的类名
            String className = joinPoint.getTarget().getClass().getName();
            myLogVO.setClassName(className);
            // 获取请求的方法名
            String methodName = method.getName();
            myLogVO.setMethodName(methodName);

            //请求uri
            String uri = request.getRequestURI();
            myLogVO.setReqUrl(uri);
            myLogVO.setReqIp(getIpAddr(request));
            //操作时间点
            myLogVO.setReqTime(getNowDate());

            //异常名称+异常信息
            if(null != e){
                myLogVO.setExcName(e.getClass().getName());
                myLogVO.setExcInfo(stackTraceToString(e.getClass().getName(), e.getMessage(), e.getStackTrace()));

            }
            //请求的参数，参数所在的数组转换成json
            String params =  Arrays.toString(joinPoint.getArgs());
            myLogVO.setParams(params);
            //返回值
            if(null != keys && Void.class.getName() != keys){
                StringBuilder result =new StringBuilder( JSONObject.toJSONString(keys));
                if(mylogProperties.getResultLength()==0){
                    //表示全部
                    myLogVO.setReturnValue(result.toString());
                }else{
                   String tempResult=result.substring(0,mylogProperties.getResultLength());
                    myLogVO.setReturnValue(tempResult);
                }
            }
            //输出日志
        } catch (Exception ex) {
           // ex.printStackTrace();
        }
        return myLogVO;
    }
    /**
     * 转换异常信息为字符串
     * @param exceptionName
     * @param exceptionMessage
     * @param elements
     * @return
     */
    private String stackTraceToString(String exceptionName, String exceptionMessage, StackTraceElement[] elements) {
        StringBuffer strbuff = new StringBuffer();
        if(mylogProperties.getExcFullShow()){
            for (StackTraceElement stet : elements) {
                strbuff.append(stet + "\n");
            }
            return exceptionName + ":" + exceptionMessage + "\n\t" + strbuff.toString();
        }
        return exceptionName+":"+exceptionMessage;
    }
    /**
     * 获取当前的时间
     * @date 2021/10/26 9:29
     * @author zk_yjl
     * @param
     * @return java.lang.String
     */
    private String getNowDate(){
        Date now=new Date();
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return simpleDateFormat.format(now);
    }

    /**
     * 获取访问者的ip地址
     * 注：要外网访问才能获取到外网地址，如果你在局域网甚至本机上访问，获得的是内网或者本机的ip
     */
    public static String getIpAddr(HttpServletRequest request) {
        String ipAddress = null;
        try {
            //X-Forwarded-For：Squid 服务代理
            String ipAddresses = request.getHeader("X-Forwarded-For");

            if (ipAddresses == null || ipAddresses.length() == 0 ||
                    "unknown".equalsIgnoreCase(ipAddresses)) {
                //Proxy-Client-IP：apache 服务代理
                ipAddresses = request.getHeader("Proxy-Client-IP");
            }

            if (ipAddresses == null || ipAddresses.length() == 0 ||
                    "unknown".equalsIgnoreCase(ipAddresses)) {
                //WL-Proxy-Client-IP：weblogic 服务代理
                ipAddresses = request.getHeader("WL-Proxy-Client-IP");
            }

            if (ipAddresses == null || ipAddresses.length() == 0 ||
                    "unknown".equalsIgnoreCase(ipAddresses)) {
                //HTTP_CLIENT_IP：有些代理服务器
                ipAddresses = request.getHeader("HTTP_CLIENT_IP");
            }

            if (ipAddresses == null || ipAddresses.length() == 0 ||
                    "unknown".equalsIgnoreCase(ipAddresses)) {
                //X-Real-IP：nginx服务代理
                ipAddresses = request.getHeader("X-Real-IP");
            }

            //有些网络通过多层代理，那么获取到的ip就会有多个，一般都是通过逗号（,）分割开来，并且第一个ip为客户端的真实IP
            if (ipAddresses != null && ipAddresses.length() != 0) {
                ipAddress = ipAddresses.split(",")[0];
            }

            //还是不能获取到，最后再通过request.getRemoteAddr();获取
            if (ipAddress == null || ipAddress.length() == 0 ||
                    "unknown".equalsIgnoreCase(ipAddresses)) {
                ipAddress = request.getRemoteAddr();
            }
        } catch (Exception e) {
            ipAddress = "";
        }
        return ipAddress;
    }
}
