package com.spring.response;

public class UserResp {
	
	private String statusCode;
	private String statusMessage;
	private String email;
	private String token;
	private String userType;
	public String getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}
	public String getStatusMessage() {
		return statusMessage;
	}
	public void setStatusMessage(String statusMessage) {
		this.statusMessage = statusMessage;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}
	public UserResp(String statusCode, String statusMessage, String email, String token, String userType) {
		super();
		this.statusCode = statusCode;
		this.statusMessage = statusMessage;
		this.email = email;
		this.token = token;
		this.userType = userType;
	}
	public UserResp() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
}
