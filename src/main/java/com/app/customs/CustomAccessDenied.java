package com.app.customs;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import com.app.services.LogServices;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class CustomAccessDenied implements AccessDeniedHandler {
	
	@Autowired
	private LogServices logServices;

	private static final ObjectMapper mapper = new ObjectMapper();
	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response,
			AccessDeniedException accessDeniedException) throws IOException, ServletException {
		// TODO Auto-generated method stub
		
		logServices.logRequest(request,null);
		response.setContentType("application/json;charset=UTF-8");
        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        PrintWriter writer = response.getWriter();
        String message = "Is not authorized";
        String xml  = mapper.writeValueAsString(new Response(403, message, accessDeniedException.getMessage())); 
        logServices.logResponse(request, response, xml);
        writer.write(xml);
        writer.flush();
        writer.close();
	}
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

}
