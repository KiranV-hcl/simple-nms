package com.hcl.simple_nms.security;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class JwtFilter implements Filter {

    private final JwtUtil jwtUtil;

    public JwtFilter(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        String path = req.getRequestURI();

		// Allow below endpoints without token
		if (path.contains("/auth/login") || path.contains("/swagger-ui") || path.contains("/v3/api-docs")
				|| path.contains("/h2-console")) {

			chain.doFilter(request, response);
			return;
		}

        String authHeader = req.getHeader("Authorization");

        // No token
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            res.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            res.getWriter().write("Missing or invalid Authorization header ❌");
            return;
        }

        String token = authHeader.substring(7);

        // Invalid token
        if (!jwtUtil.isValid(token)) {
            res.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            res.getWriter().write("Invalid JWT Token ❌");
            return;
        }

        // Valid token → proceed
        chain.doFilter(request, response);
    }
}