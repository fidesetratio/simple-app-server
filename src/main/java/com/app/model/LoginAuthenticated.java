package com.app.model;

public class LoginAuthenticated {
		
			private Integer status;
			private String message;
			private String token;
			
			
			public LoginAuthenticated(Integer status, String message) {
				this.status = status;
				this.message = message;
			}
			public LoginAuthenticated() {
				super();
			}
			
			public Integer getStatus() {
				return status;
			}
			public void setStatus(Integer status) {
				this.status = status;
			}
			public String getMessage() {
				return message;
			}
			public void setMessage(String message) {
				this.message = message;
			}
			public String getToken() {
				return token;
			}
			public void setToken(String token) {
				this.token = token;
			}
			
}
