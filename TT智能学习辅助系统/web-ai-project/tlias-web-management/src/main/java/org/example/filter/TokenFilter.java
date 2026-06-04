package org.example.filter;

import io.jsonwebtoken.Claims;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.example.pojo.EmpLoginLog;
import org.example.utils.CurrentHolder;
import org.example.utils.JwtUtils;

import java.io.IOException;


@Slf4j
@WebFilter(urlPatterns = "/*")
public class TokenFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        //1.获取到请求路径
        String requestURI = request.getRequestURI();

        //2，判断是否是登录请求，如果是登录请求，则放行
        if(requestURI.contains("/login")){
            log.info("登录请求: {}", requestURI);
            filterChain.doFilter(request,response);
            return;
        }

        //3.获取请求头中的token
        String token = request.getHeader("token");

        //4.判断token是否存在，如果存在，则放行，不存在则返回401状态码
        if(token == null || token.isEmpty()){
            log.info("请求头token不存在");
            response.setStatus(401);
            return;
        }

        EmpLoginLog ell = new EmpLoginLog();

        //5.如果token存在，校验令牌，如果校验失败->返回401状态码，如果校验成功，则放行
        try{
            JwtUtils.parseToken(token);
            //获取用户id
            Integer empId = (Integer) JwtUtils.parseToken(token).get("id");
            log.info("当前用户id: {}", empId);
            //将用户id存储到请求域中
            CurrentHolder.setCurrent(ell);
        }catch (Exception e){
            log.info("token校验失败");
            response.setStatus(401);
            return;
        }

        //6.校验成功，放行
        log.info("令牌校验通过");
        filterChain.doFilter(request,response);

        //7.清空当前线程中的用户id
        CurrentHolder.remove();
    }

}
