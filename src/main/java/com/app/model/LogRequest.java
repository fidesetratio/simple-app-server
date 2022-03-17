package com.app.model;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.IOUtils;

public class LogRequest {
	
	private String ip;
	private String url;
	private String method;
	
	private String parameter;
	private String body;
	private String uuid;
	private String userAgent;
	
	
	
	private List<RequestHeader> headers;
	
	
	
	public LogRequest() {
		super();
		this.headers = new ArrayList<RequestHeader>();
	}
	
	
	
	
	
	public static LogRequest createLogRequest(HttpServletRequest httpServletRequest) {
		LogRequest logRequest = new LogRequest();	
		Object unique = httpServletRequest.getAttribute("unique") == null? "": httpServletRequest.getAttribute("unique");
		String remoteUrl = httpServletRequest.getRemoteAddr();
		String requestUrl =httpServletRequest.getRequestURI() !=  null ? httpServletRequest.getRequestURI().toString():"" ;
		String userAgent = httpServletRequest.getHeader("User-Agent");
		String method = httpServletRequest.getMethod();
		logRequest.setUuid(unique != null? unique.toString():"");
		logRequest.setUrl(requestUrl);
		logRequest.setIp(remoteUrl);
		logRequest.setUserAgent(userAgent);
		logRequest.setMethod(method);
		try {
			String requestBody = IOUtils.toString(httpServletRequest.getInputStream(), Charset.forName("UTF-8").toString());
			logRequest.setBody(requestBody);
		} catch (IOException e) {
			// TODO Auto-generated catch block

		}

		
		String parameter = "";
	    Enumeration<String> headerNames = httpServletRequest.getHeaderNames();
	    ArrayList<RequestHeader> headers = new ArrayList<RequestHeader>();
		
	    
	    if(method.toUpperCase().equals("GET")) {
			parameter = getMethodParameter(httpServletRequest);
		}
		
		 if (headerNames != null) {
	            while (headerNames.hasMoreElements()) {
	            		String key = headerNames.nextElement();
		           
	            		String value = httpServletRequest.getHeader(key);
	            		RequestHeader header = new RequestHeader(key, value);
	            		headers.add(header);
	            }
	    }
		 
		 logRequest.setHeaders(headers);
		 
		logRequest.setParameter(parameter);
		return logRequest;
	}
	
	public static LogRequest createLogRequest(HttpServletRequest httpServletRequest, String body) {
		LogRequest logRequest = new LogRequest();		
		return logRequest;
	}
	
	
	
	public static String getMethodParameter(HttpServletRequest request) {
		Enumeration<String> parameterNames = request.getParameterNames();
		
		StringBuffer buffer = new StringBuffer();
		while (parameterNames.hasMoreElements()) {
            String paramName = parameterNames.nextElement();
            String[] paramValues = request.getParameterValues(paramName);
            for(String s:paramValues) {
            	buffer.append(paramName+"="+s+"&");
            };
        }
        return buffer.toString().substring(0,buffer.toString().length()-1);
 
    }
 
	
	
	public LogRequest(String uuid,String ip, String url, String method, String parameter, String body) {
		super();
		this.ip = ip;
		this.url = url;
		this.method = method;
		this.parameter = parameter;
		this.body = body;
		this.uuid = uuid;
	}
	
	
	
	
	
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getMethod() {
		return method;
	}
	public void setMethod(String method) {
		this.method = method;
	}
	public String getParameter() {
		return parameter;
	}
	public void setParameter(String parameter) {
		this.parameter = parameter;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}


	public String getUuid() {
		return uuid;
	}


	public void setUuid(String uuid) {
		this.uuid = uuid;
	}









	public String getUserAgent() {
		return userAgent;
	}





	public void setUserAgent(String userAgent) {
		this.userAgent = userAgent;
	}










	public List<RequestHeader> getHeaders() {
		return headers;
	}





	public void setHeaders(List<RequestHeader> headers) {
		this.headers = headers;
	}





	@Override
	public String toString() {
		return "LogRequest [ip=" + ip + ", url=" + url + ", method=" + method + ", parameter=" + parameter + ", body="
				+ body + ", uuid=" + uuid + ", userAgent=" + userAgent + ", headers=" + headers + "]";
	}
	
	

}
