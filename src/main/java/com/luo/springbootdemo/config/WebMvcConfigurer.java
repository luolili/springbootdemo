package com.luo.springbootdemo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration//must add this anno
public class WebMvcConfigurer extends WebMvcConfigurerAdapter {
    //add interceptors
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //add the path that will intercept
        registry.addInterceptor(new OneInterceptor()).addPathPatterns("/one/**");
        super.addInterceptors(registry);
    }
}
