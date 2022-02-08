package com.spring.viewModel;

import com.spring.pojo.Patient;
import com.spring.pojo.PatientInformation;

public class PatientAndPatientInformationBody {
	
	private Patient patient;
	private PatientInformation patientInformation;
	public Patient getPatient() {
		return patient;
	}
	public void setPatient(Patient patient) {
		this.patient = patient;
	}
	public PatientInformation getPatientInformation() {
		return patientInformation;
	}
	public void setPatientInformation(PatientInformation patientInformation) {
		this.patientInformation = patientInformation;
	}
	@Override
	public String toString() {
		return "PatientAndPatientInformationBody [patient=" + patient + ", patientInformation=" + patientInformation
				+ "]";
	}
	public PatientAndPatientInformationBody() {}
	
}
