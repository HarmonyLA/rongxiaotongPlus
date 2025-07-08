package com.ldk.config;


import com.ldk.intercepoter.LoginInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class LoginInterceptorConfig implements WebMvcConfigurer {


    @Autowired
    LoginInterceptor loginInterceptor;
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
       registry.addInterceptor(loginInterceptor)
               .addPathPatterns("/**")// 拦截所有请求
               .excludePathPatterns("/user/login")  // 放行这些请求
               .excludePathPatterns("/swagger-ui/**")
               .excludePathPatterns("/user/add");   // 放行这些请求
    }
}
