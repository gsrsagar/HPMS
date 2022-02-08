package com.spring.viewModel;

import java.io.Serializable;

public class GetTestPatientList implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 9093516613866101831L;
	private long patientId;
	private String mobileNumber;
	private String firstName;
	private long lastName;
	private String testId;
	private String testName;
	public long getPatientId() {
		return patientId;
	}
	public void setPatientId(long patientId) {
		this.patientId = patientId;
	}
	public String getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public long getLastName() {
		return lastName;
	}
	public void setLastName(long lastName) {
		this.lastName = lastName;
	}
	public String getTestId() {
		return testId;
	}
	public void setTestId(String testId) {
		this.testId = testId;
	}
	public String getTestName() {
		return testName;
	}
	public void setTestName(String testName) {
		this.testName = testName;
	}
	@Override
	public String toString() {
		return "RadiologyGetPatientList [patientId=" + patientId + ", mobileNumber=" + mobileNumber + ", firstName="
				+ firstName + ", lastName=" + lastName + ", testId=" + testId + ", testName=" + testName + "]";
	}
	
	
	
}
