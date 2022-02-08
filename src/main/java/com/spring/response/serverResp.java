package com.spring.response;

public class serverResp {
	private String status;
	private String message;
	private String AUTH_TOKEN;
	private String userType;
	private String userName;
	private String email;
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
	public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	@Override
	public String toString() {
		return "serverResp [status=" + status + ", message=" + message + ", AUTH_TOKEN=" + AUTH_TOKEN + ", userType="
				+ userType + ", userName=" + userName + ", email=" + email +"]";
	}
	
	

}
