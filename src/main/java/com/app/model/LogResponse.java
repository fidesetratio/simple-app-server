package com.app.model;

import javax.servlet.http.HttpServletResponse;

public class LogResponse {

	private String ip;
	private String url;
	private String method;
	
	private String parameter;
	private String body;
	private String uuid;
	
	private Integer status;
	public LogResponse() {
		super();
	}
	
	
	
	
	
	public static LogResponse createLogResponse(HttpServletResponse httpServletResponse) {
		LogResponse logResponse = new LogResponse();	
		
		
		return logResponse;
	}
	public static LogResponse createLogResponse(HttpServletResponse httpServletResponse, String body) {
		LogResponse logResponse = new LogResponse();		
		
		logResponse.setBody(body);
		return logResponse;
	}
	
	public static LogResponse createLogResponse(HttpServletResponse httpServletResponse, String body, String unique) {
		LogResponse logResponse = new LogResponse();		
		logResponse.setUuid(unique);
		logResponse.setBody(body);
		 Integer status = httpServletResponse.getStatus();
		 logResponse.setStatus(status);
		return logResponse;
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









	public Integer getStatus() {
		return status;
	}





	public void setStatus(Integer status) {
		this.status = status;
	}





	@Override
	public String toString() {
		return "LogResponse [ip=" + ip + ", url=" + url + ", method=" + method + ", parameter=" + parameter + ", body="
				+ body + ", uuid=" + uuid + ", status=" + status + "]";
	}
	
	

}
