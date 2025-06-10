package com.his.heal.config;

import com.his.heal.filter.AtHeaderFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfig {

    @Bean
    public FilterRegistrationBean<AtHeaderFilter> atHeaderFilterRegistration() {
        FilterRegistrationBean<AtHeaderFilter> registration = new FilterRegistrationBean<>();
        registration.setFilter(new AtHeaderFilter());
        registration.addUrlPatterns("/*");  // 拦截所有请求
        registration.setOrder(1);           // 设置执行顺序
        return registration;
    }
}