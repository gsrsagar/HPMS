package com.spring.response;

import java.util.List;

import com.spring.viewModel.PatientAndPatientInformationBody;

public class getAllPatientsResponse {
	private String status;
	private String message;
	private String AUTH_TOKEN;
	private List<PatientAndPatientInformationBody> patientInfoBody;
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
	public List<PatientAndPatientInformationBody> getPatientInfoBody() {
		return patientInfoBody;
	}
	public void setPatientInfoBody(List<PatientAndPatientInformationBody> patientInfoBody) {
		this.patientInfoBody = patientInfoBody;
	}
	@Override
	public String toString() {
		return "getAllPatientsResponse [status=" + status + ", message=" + message + ", AUTH_TOKEN=" + AUTH_TOKEN
				+ ", patientInfoBody=" + patientInfoBody + "]";
	}
	
	
	
}
