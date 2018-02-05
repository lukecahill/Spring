package com.lukecahill.spring.config;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.filter.OncePerRequestFilter;

@CrossOrigin(origins = "*")
@Order(-1)
@Component
public class SimpleCorsFilter extends OncePerRequestFilter {

	static final String ORIGIN = "ENTER_ORIGIN_HERE";

	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Credentials", "true");
		response.setHeader("Access-Control-Allow-Methods", "PUT, POST, GET, OPTIONS, DELETE");
		response.setHeader("Access-Control-Max-Age", "3600");
		response.setHeader("Access-Control-Allow-Headers", "content-type, authorization");

		boolean status = "OPTIONS".equalsIgnoreCase(request.getMethod());

		if (status)
			response.setStatus(HttpServletResponse.SC_OK);
		else
			filterChain.doFilter(request, response);

	}
}