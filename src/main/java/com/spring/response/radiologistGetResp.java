package com.spring.response;

import java.io.Serializable;
import java.util.List;

import org.hibernate.annotations.Any;

import com.spring.viewModel.*;

public class radiologistGetResp implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7166394855562307094L;
	private String status;
	private String message;
	private String AUTH_TOKEN;
	private List<GetTestPatientList> testList;
	
	
	public List<GetTestPatientList> getTestList() {
		return testList;
	}
	public void setTestList(List<GetTestPatientList> testList) {
		this.testList = testList;
	}
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
		return "radiologistGetResp [status=" + status + ", message=" + message + ", AUTH_TOKEN=" + AUTH_TOKEN
				+ ", testList=" + testList + "]";
	}
}
