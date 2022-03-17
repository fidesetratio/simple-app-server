package com.app.model;

public class ResponseServices {
	
		private Integer status;
		private String message;
		
		public ResponseServices(Integer status, String message) {
			this.status = status;
			this.message = message;
		}
		
		public ResponseServices() {
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
		

}
