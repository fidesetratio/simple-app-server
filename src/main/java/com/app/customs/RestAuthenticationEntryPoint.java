package com.app.customs;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import com.app.services.LogServices;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class RestAuthenticationEntryPoint implements AuthenticationEntryPoint{
	private static final ObjectMapper mapper = new ObjectMapper();
	
	@Autowired
	private LogServices logServices;
	


	class Response{
		public Response(int status, String message, String error) {
			super();
			this.status = status;
			this.message = message;
			this.error = error;
		}
		public int getStatus() {
			return status;
		}
		public void setStatus(int status) {
			this.status = status;
		}
		public String getMessage() {
			return message;
		}
		public void setMessage(String message) {
			this.message = message;
		}
		public String getError() {
			return error;
		}
		public void setError(String error) {
			this.error = error;
		}
		private int status;
		private String message;
		private String error;
	}


	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException e) throws IOException, ServletException {
		// TODO Auto-generated method stub
			response.setContentType("application/json;charset=UTF-8");
	        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
	        logServices.logRequest(request, null);
	        PrintWriter writer = response.getWriter();
	        String message = "Is not authorized";
	        Response responses = new Response(401, message, e.getMessage());
	        String xml = mapper.writeValueAsString(responses);
	        logServices.logResponse(request, response, xml);
	        writer.write(xml);
	        writer.flush();
	        writer.close();
	        
	}
	
}
