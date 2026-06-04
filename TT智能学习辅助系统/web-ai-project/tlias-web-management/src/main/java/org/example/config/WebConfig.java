package org.example.config;

import org.example.interceptor.DemoIntercepter;
import org.example.interceptor.TokenInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//配置类，用于配置拦截器
/*@Configuration
public class WebConfig implements WebMvcConfigurer {
//    @Autowired
//    private DemoIntercepter demoIntercepter;
    @Autowired
    private TokenInterceptor tokenInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(tokenInterceptor).addPathPatterns("/**");
    }
}*/
