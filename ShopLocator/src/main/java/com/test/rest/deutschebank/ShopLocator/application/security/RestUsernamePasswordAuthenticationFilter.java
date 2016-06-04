package com.test.rest.deutschebank.ShopLocator.application.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * Authentication filter for REST services
 */
public class RestUsernamePasswordAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

	private static final Log LOGGER = LogFactory.getLog(RestUsernamePasswordAuthenticationFilter.class);
	
	@Override
	protected boolean requiresAuthentication(HttpServletRequest request, HttpServletResponse response) {
		boolean retVal = false;
		String username = request.getParameter("j_username");
		String password = request.getParameter("j_password");
		if (username != null && password != null) {
			Authentication authResult = null;
			try {
				authResult = attemptAuthentication(request, response);
				if (authResult == null) {
					retVal = false;
				}
			} catch (AuthenticationException failed) {
				LOGGER.warn(failed.getMessage(), failed);
			}
		} else {
			retVal = true;
		}
		return retVal;
	}
	
	
	@Override 
	public Authentication attemptAuthentication(javax.servlet.http.HttpServletRequest request,
            javax.servlet.http.HttpServletResponse response)
              throws AuthenticationException{
		Authentication result = null;
		String username = request.getParameter("j_username");
		String password = request.getParameter("j_password");
		if((username == "normal_user" && password =="password") ||(username == "normal_user" && password =="password")){
			result = new UsernamePasswordAuthenticationToken(username, password);
		}
		return result;
		
	}
}