package org.example.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import lombok.extern.slf4j.Slf4j;
import java.io.IOException;

//@WebFilter(urlPatterns = "/*")
@Slf4j
public class DemoFilter implements Filter {
    //初始化方法，web应用启动时,执行一次
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("init 初始化方法...");
    }

    @Override
    //拦截到请求时，执行，会执行多次
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        log.info("拦截到了请求...放行前");
        //放行
        filterChain.doFilter(servletRequest,servletResponse);
        log.info("拦截到了请求...放行后");
    }

    @Override
    //销毁方法，web应用停止时，执行一次
    public void destroy() {
        log.info("destroy 销毁方法...");
    }
}
