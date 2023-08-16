package top.yueshushu.learn.config;


import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * 用途描述
 *
 * @author yuejianli
 * @date 2023-04-04
 */
@Slf4j
public class LogFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        long start = System.currentTimeMillis();
        chain.doFilter(request, response);
        log.info("Execute cost= {}" ,(System.currentTimeMillis() - start));
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
