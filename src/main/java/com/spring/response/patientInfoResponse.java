package com.spring.response;

import java.util.List;

import com.spring.pojo.PatientInformation;


public class patientInfoResponse {

	private String status;
	private String message;
	private String AUTH_TOKEN;
	private PatientInformation patientInfoList;
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
	public PatientInformation getPatientInfoList() {
		return patientInfoList;
	}
	public void setPatientInfoList(PatientInformation patientInfoList) {
		this.patientInfoList = patientInfoList;
	}
	@Override
	public String toString() {
		return "patientInfoResponse [status=" + status + ", message=" + message + ", AUTH_TOKEN=" + AUTH_TOKEN
				+ ", patientInfoList=" + patientInfoList + "]";
	}
	public patientInfoResponse(String status, String message, String aUTH_TOKEN,
			PatientInformation patientInfoList) {
		this.status = status;
		this.message = message;
		AUTH_TOKEN = aUTH_TOKEN;
		this.patientInfoList = patientInfoList;
	}
	
	public patientInfoResponse() {}
	
}
