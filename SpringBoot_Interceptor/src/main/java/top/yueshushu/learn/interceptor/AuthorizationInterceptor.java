package top.yueshushu.learn.interceptor;

/**
 * @author yuejianli
 * @Date: 2022-05-20
 */
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.http.MediaType;
import org.springframework.util.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import top.yueshushu.learn.annotation.AuthToken;
import top.yueshushu.learn.model.User;
import top.yueshushu.learn.util.ThreadLocalUtils;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.lang.reflect.Method;


@Slf4j
public class AuthorizationInterceptor implements HandlerInterceptor {

    private String httpHeaderName = "Authorization";
    public static final String X_REAL_IP = "x-real-ip";
    public static final String REQUEST_CURRENT_KEY = "REQUEST_CURRENT_KEY";


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();
        boolean isForAllTimeUser = false;
        if (method.getAnnotation(AuthToken.class) != null ||
                handlerMethod.getBeanType().getAnnotation(AuthToken.class) != null) {

            String token = request.getHeader(httpHeaderName);
            String ip = request.getHeader(X_REAL_IP);
            User loginUser = null;
            if (StringUtils.hasText(token)) {
                // 根据 token 获取用户 信息
                // 验证 token 是否 jwt 过期, 过期的话, isFalse
            }
            if (!StringUtils.hasText(token) || loginUser == null) {
                isFalse(request, response);
                return false;
            }
            String userDoAccount = loginUser.getAccount();
            ThreadLocalUtils.put("userDoAccount", userDoAccount);
            ThreadLocalUtils.put("ip", ip == null ? request.getRemoteAddr() : ip);
            ThreadLocalUtils.put("userName", loginUser.getAccount());
            ThreadLocalUtils.put("user", loginUser);
            ThreadLocalUtils.put("token", token);
            ThreadLocalUtils.put("userId", loginUser.getId());

            // 可以进行续命, 将时间延长.

            return true;
        }
        request.setAttribute(REQUEST_CURRENT_KEY, null);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) {
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        ThreadLocalUtils.release();
    }

    private void isFalse(HttpServletRequest httpServletRequest, HttpServletResponse response) {
        JSONObject jsonObject = new JSONObject();
        PrintWriter out = null;
        try {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
            response.setCharacterEncoding("utf-8");
            jsonObject.put("code", "10010001");
            jsonObject.put("message", "登录已过期,请重新登录");
            //添加其余的两个属性  success和 data
            jsonObject.put("data", "");
            jsonObject.put("success", false);
            out = response.getWriter();
            out.println(jsonObject);
        } catch (Exception e) {
            log.error("发生异常{}", e);
        } finally {
            if (out != null) {
                out.flush();
                out.close();
            }
        }
    }
    private void isUnValid(HttpServletRequest httpServletRequest, HttpServletResponse response) {
        JSONObject jsonObject = new JSONObject();
        PrintWriter out = null;
        try {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
            response.setCharacterEncoding("utf-8");
            jsonObject.put("code", "10010002");
            jsonObject.put("message", "您的账号已经在另一处登录了,您被迫下线!");
            //添加其余的两个属性  success和 data, 解决 PDA端无法翻译 退出的问题。 @zk_yjl
            jsonObject.put("data", "");
            jsonObject.put("success", false);
            out = response.getWriter();
            out.println(jsonObject);
        } catch (Exception e) {
            log.error("发生异常", e);
        } finally {
            if (out != null) {
                out.flush();
                out.close();
            }
        }
    }
}
