package com.spring.viewModel;

public class PatientPrescription {

	private long patientId;
	private String prescription;
	public long getPatientId() {
		return patientId;
	}
	public void setPatientId(long patientId) {
		this.patientId = patientId;
	}
	public String getPrescription() {
		return prescription;
	}
	public void setPrescription(String prescription) {
		this.prescription = prescription;
	}
	@Override
	public String toString() {
		return "PatientPrescription [patientId=" + patientId + ", prescription=" + prescription + "]";
	}
	
	
}
