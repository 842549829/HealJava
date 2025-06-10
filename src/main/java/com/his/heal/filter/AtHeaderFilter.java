package com.his.heal.filter;

import com.his.heal.provider.at.contract.AtProvider;
import com.his.heal.provider.at.imp.DefaultAtProvider;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import java.io.IOException;

public  class AtHeaderFilter implements Filter {

    // 用来保存 FilterConfig
    private FilterConfig filterConfig;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.filterConfig = filterConfig; // 保存 FilterConfig
    }


    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        String atHeaderValue = httpRequest.getHeader("At");

        // 获取 Spring 上下文
        WebApplicationContext context = WebApplicationContextUtils.getRequiredWebApplicationContext(filterConfig.getServletContext());

        // 创建 AtProvider 实例
        AtProvider atProvider = new DefaultAtProvider(atHeaderValue);

        // 将 AtProvider 设置为当前请求属性，供后续组件获取
        httpRequest.setAttribute("atProvider", atProvider);

        filterChain.doFilter(servletRequest, servletResponse);
    }
}
