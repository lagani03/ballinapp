package com.ballinapp.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * User: dusan <br/> Date: 3/20/18
 */
@Configuration
public class AppConfig extends WebMvcConfigurerAdapter {

    @Autowired
    DefaultRequestInterceptor requestInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(requestInterceptor).excludePathPatterns("/register/*");
    }
}
