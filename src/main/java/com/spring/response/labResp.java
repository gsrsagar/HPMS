package com.spring.response;


import java.io.Serializable;
import java.util.List;

import com.spring.pojo.Laboratory;

public class labResp implements Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = 6427576912832436672L;
	private String status;
	private String message;
	private String AUTH_TOKEN;
	private List<Laboratory> laboratoryList;
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
	public List<Laboratory> getLaboratoryList() {
		return laboratoryList;
	}
	public void setLaboratoryList(List<Laboratory> laboratoryList) {
		this.laboratoryList = laboratoryList;
	}
	
	@Override
	public String toString() {
		return "labResp [status=" + status + ", message=" + message + ", AUTH_TOKEN=" + AUTH_TOKEN + ", laboratoryList="
				+ laboratoryList + "]";
	}

	
}
