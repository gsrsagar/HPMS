package com.spring.viewModel;

import java.util.List;

import com.spring.pojo.Tests;

public class PatientTests {

	private long patientId;
	private List<Tests> tests;
	public long getPatientId() {
		return patientId;
	}
	public void setPatientId(long patientId) {
		this.patientId = patientId;
	}
	public List<Tests> getTests() {
		return tests;
	}
	public void setTests(List<Tests> tests) {
		this.tests = tests;
	}
	@Override
	public String toString() {
		return "PatientTests [patientId=" + patientId + ", tests=" + tests + "]";
	}
	
	
	
}
