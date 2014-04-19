package com.ysb.model.service;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GatewayFilter implements Filter {

    @Override
    public void destroy() {}

    @Override
    public void init(FilterConfig config) throws ServletException {}

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        String uri = httpRequest.getRequestURI();

        try {
            if (AuthService.isLogged(httpRequest) || uri.endsWith("/login") || uri.contains("/res/") || uri.contains(".jsp")) {
                chain.doFilter(request, response);
            } else {
                httpResponse.sendRedirect("/login");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}