package com.spring.response;

public class testPostResponse {
	
	private String status;
	private String message;
	private String AUTH_TOKEN;
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getAUTH_TOKEN() {
		return AUTH_TOKEN;
	}
	public void setAUTH_TOKEN(String aUTH_TOKEN) {
		AUTH_TOKEN = aUTH_TOKEN;
	}
	@Override
	public String toString() {
		return "testPostResponse [status=" + status + ", message=" + message + ", AUTH_TOKEN=" + AUTH_TOKEN + "]";
	}
	
	
	
}
