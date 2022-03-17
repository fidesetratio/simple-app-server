package com.app.customs;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
public class CustomJwtTokenAuthenticationFilter extends OncePerRequestFilter {
	
	private static final Logger logger = LoggerFactory.getLogger(CustomJwtTokenAuthenticationFilter.class);

    @Value("${username.token}")
    private String tokenstatic;
    
    @Value("${username.contains.token}")
	private String containsToken;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		  	String token = request.getHeader("Authorization");
	        SecurityContextHolder.clearContext();
	        if (token != null && token.startsWith("Bearer" + " ")) {
	            token = token.replace("Bearer" + " ", "");
	            logger.info(" token :"+token);
	              if(token.trim().contains(containsToken)) {      
			            if(token.trim().equals(tokenstatic)) {
				            List<SimpleGrantedAuthority> grantedRoles = new ArrayList<SimpleGrantedAuthority>();
				            grantedRoles.add(new SimpleGrantedAuthority("ROLE_USER"));
				            UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken("TEST", null, grantedRoles);
			                SecurityContextHolder.getContext().setAuthentication(auth);
				         }
			      }else {
			    	  logger.info("you don't have any authentication");
			      }
			      
			      
			      
	        };
	        filterChain.doFilter(request, response);

	}



}
