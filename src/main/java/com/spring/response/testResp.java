package com.spring.response;


import java.io.Serializable;
import java.util.List;

import com.spring.pojo.Tests;


public class testResp implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3313907500941802554L;
	private String status;
	private String message;
	private String AUTH_TOKEN;
	private List<Tests> testList;
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
	public List<Tests> getTestList() {
		return testList;
	}
	public void setTestList(List<Tests> testList) {
		this.testList = testList;
	}
	@Override
	public String toString() {
		return "testResp [status=" + status + ", message=" + message + ", AUTH_TOKEN=" + AUTH_TOKEN + ", testList="
				+ testList + "]";
	}
	
}
