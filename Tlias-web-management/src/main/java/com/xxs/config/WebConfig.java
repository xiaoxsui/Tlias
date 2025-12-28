package com.xxs.config;

import com.xxs.interceptor.DemoInterceptor;
import com.xxs.interceptor.TokenInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/*//配置类
@Slf4j
@Configuration
public class WebConfig implements WebMvcConfigurer {

*//*    @Autowired
    private DemoInterceptor demoInterceptor;*//*

    @Autowired
    private TokenInterceptor tokenInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(tokenInterceptor)
                .addPathPatterns("/**")  // 拦截所有请求
                .excludePathPatterns("/login"); // 登录接口不拦截
    }
}*/
