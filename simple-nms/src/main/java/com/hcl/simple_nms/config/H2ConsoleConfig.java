package com.hcl.simple_nms.config;

import jakarta.servlet.Servlet;
import org.h2.server.web.JakartaWebServlet;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class H2ConsoleConfig {

    @Bean
    public ServletRegistrationBean<Servlet> h2servletRegistration() {
        ServletRegistrationBean<Servlet> registration =
                new ServletRegistrationBean<>(new JakartaWebServlet());

        registration.addUrlMappings("/h2-console/*");

        return registration;
    }
}