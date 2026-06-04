package org.example.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.example.utils.JwtUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Slf4j
//@Component
public class TokenInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //1.获取到请求路径
        String requestURI = request.getRequestURI();

        //2，判断是否是登录请求，如果是登录请求，则放行
        if(requestURI.contains("/login")){
            log.info("登录请求: {}", requestURI);
            return true;
        }

        //3.获取请求头中的token
        String token = request.getHeader("token");

        //4.判断token是否存在，如果存在，则放行，不存在则返回401状态码
        if(token == null || token.isEmpty()){
            log.info("请求头token不存在");
            response.setStatus(401);
            return  false;
        }

        //5.如果token存在，校验令牌，如果校验失败->返回401状态码，如果校验成功，则放行
        try{
            JwtUtils.parseToken(token);
        }catch (Exception e){
            log.info("token校验失败");
            response.setStatus(401);
            return  false;
        }

        //6.校验成功，放行
        log.info("令牌校验通过");
        return  true;
    }
}
