package com.spring.response;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import com.spring.pojo.Patient;



public class patientResp implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4247711894936982190L;
	private String status;
	private String message;
	private String AUTH_TOKEN;
	private List<Patient> patientList;
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
	public List<Patient> getPatientList() {
		return patientList;
	}
	public void setPatientList(List<Patient> patientList) {
		this.patientList = patientList;
	}
	@Override
	public String toString() {
		return "patientResp [status=" + status + ", message=" + message + ", AUTH_TOKEN=" + AUTH_TOKEN
				+ ", patientList=" + patientList + "]";
	}
	
}