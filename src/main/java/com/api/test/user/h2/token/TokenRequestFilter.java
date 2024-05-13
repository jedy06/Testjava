package com.api.test.user.h2.token;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.api.test.user.h2.entity.User;
import com.api.test.user.h2.service.UserService;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;

import java.io.IOException;
import java.util.Date;

@Component
public class TokenRequestFilter implements Filter {
	

	private final UserService userService;
	
	
	 public TokenRequestFilter(UserService userService) {
	        this.userService = userService;
//			this.tokenService = tokenService;
	    }
	
	
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        if (request instanceof HttpServletRequest && ((HttpServletRequest) request).getRequestURI().equals("/oauth/token")) {
        }
        	String email = request.getParameter("username");
        	
        	chain.doFilter(request, response);
        	User us = userService.obtenerPorEmail(email);
        	if (us == null) {
        		throw new ServletException("usuario no encontrado");
        	}
        	userService.actualizarToken(us);       
			        
    }

 
}