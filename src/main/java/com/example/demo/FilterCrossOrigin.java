package com.example.demo;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class FilterCrossOrigin extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest,
                                    HttpServletResponse httpServletResponse,
                                    FilterChain filterChain)
            throws ServletException, IOException {

        String headerFromNginx=httpServletRequest.getHeader("sks-personal-nginx-header-2");
        System.out.println(">>> headerFromNginx="+headerFromNginx);

        httpServletResponse.addHeader("Access-Control-Allow-Origin", "*");
        httpServletResponse.addHeader("sks-personal-springboot-header", "going through springboot filter");
        httpServletResponse.addHeader("sks-personal-nginx-header-2", headerFromNginx);
        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }
}