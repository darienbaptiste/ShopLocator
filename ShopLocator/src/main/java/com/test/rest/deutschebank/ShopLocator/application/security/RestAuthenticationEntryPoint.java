package com.test.rest.deutschebank.ShopLocator.application.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

/**
 * Authentication entry point for REST services
 */
public final class RestAuthenticationEntryPoint implements AuthenticationEntryPoint {

	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) throws IOException, ServletException {
		System.out.println(" here in RestAuthenticationEntryPoint");
		response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");

	}

}