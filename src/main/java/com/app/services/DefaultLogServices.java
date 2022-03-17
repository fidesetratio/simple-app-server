package com.app.services;

import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jboss.logging.Logger;
import org.springframework.stereotype.Component;

import com.app.model.LogRequest;
import com.app.model.LogResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class DefaultLogServices implements LogServices{
	
	private static Logger logger = Logger.getLogger(DefaultLogServices.class);
	private static final ObjectMapper mapper = new ObjectMapper();

	@Override
	public void logRequest(HttpServletRequest httpServletRequest, Object body) {
		StringBuffer buffer =  new StringBuffer();
		httpServletRequest.setAttribute("unique", UUID.randomUUID().toString());
		LogRequest request = LogRequest.createLogRequest(httpServletRequest);
		System.out.println(request);
		
		
	}

	@Override
	public void logResponse(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
			Object body) {
		// TODO Auto-generated method stub
		StringBuffer buffer =  new StringBuffer();
	
		
		buffer.append(" IP : "+httpServletRequest.getRemoteAddr());
		buffer.append(" URL : "+httpServletRequest.getRequestURL().toString());
		buffer.append(" METHOD:"+httpServletRequest.getMethod());
		buffer.append(" ATTRIBUTE:"+httpServletRequest.getAttribute("unique"));
	
		buffer.append(" Content Type : "+httpServletResponse.getContentType());
		String unique = httpServletRequest.getAttribute("unique") != null ? httpServletRequest.getAttribute("unique").toString():"";
		
		String json = null;
		try {
			json = mapper.writeValueAsString(body);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();\
			json = body.toString();
			
		}
	       
		buffer.append(" Response:"+json);
		LogResponse response = LogResponse.createLogResponse(httpServletResponse,json,unique);
		System.out.println(response);
		logger.info("response:"+buffer.toString());
			
	}

}
