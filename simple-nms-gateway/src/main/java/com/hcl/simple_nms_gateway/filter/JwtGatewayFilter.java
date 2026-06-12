package com.hcl.simple_nms_gateway.filter;

import com.hcl.simple_nms_gateway.security.JwtUtil;
import com.hcl.simple_nms_gateway.service.RateLimiterService;

import org.springframework.stereotype.Component;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
public class JwtGatewayFilter implements GlobalFilter {

	private final JwtUtil jwtUtil;

	private final RateLimiterService rateLimiter;

	public JwtGatewayFilter(JwtUtil jwtUtil, RateLimiterService rateLimiter) {
		this.jwtUtil = jwtUtil;
		this.rateLimiter = rateLimiter;
	}

	@Override
	public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

		System.out.println(" Gateway Filter Hit");

		String path = exchange.getRequest().getURI().getPath();

		// Whitelist endpoints
		if (path.contains("/auth/login") || path.contains("/swagger-ui") || path.contains("/v3/api-docs")
				|| path.contains("/h2-console")) {

			return chain.filter(exchange);
		}

		// ADD RATE LIMIT LOGIC HERE 

		String clientIp = exchange.getRequest().getRemoteAddress().getAddress().getHostAddress();

		if (!rateLimiter.isAllowed(clientIp)) {

			System.out.println("Rate limit exceeded for IP: " + clientIp);

			exchange.getResponse().setStatusCode(org.springframework.http.HttpStatus.TOO_MANY_REQUESTS);

			return exchange.getResponse().setComplete();
		}

		// JWT Validation starts here

		String authHeader = exchange.getRequest().getHeaders().getFirst("Authorization");

		if (authHeader == null || !authHeader.startsWith("Bearer ")) {

			exchange.getResponse().setStatusCode(org.springframework.http.HttpStatus.UNAUTHORIZED);

			return exchange.getResponse().setComplete();
		}

		String token = authHeader.substring(7);

		if (!jwtUtil.isValid(token)) {

			exchange.getResponse().setStatusCode(org.springframework.http.HttpStatus.UNAUTHORIZED);

			return exchange.getResponse().setComplete();
		}

		// Valid request
		return chain.filter(exchange);
	}
}