package com.spring.viewModel;

import java.util.List;

import com.spring.pojo.Files;



public class TestsPostRequest {

	private long patientId;
	private String patientMobileNo;
	private String patientFullName;
	private long testId;
	private String testName;
	private List<Files> scanReports;
	private List<Files> bills;
	
	
	
	
	public List<Files> getBills() {
		return bills;
	}
	public void setBills(List<Files> bills) {
		this.bills = bills;
	}
	public List<Files> getScanReports() {
		return scanReports;
	}
	public void setScanReports(List<Files> scanReports) {
		this.scanReports = scanReports;
	}
	public long getPatientId() {
		return patientId;
	}
	public void setPatientId(long patientId) {
		this.patientId = patientId;
	}
	public String getPatientMobileNo() {
		return patientMobileNo;
	}
	public void setPatientMobileNo(String patientMobileNo) {
		this.patientMobileNo = patientMobileNo;
	}
	public String getPatientFullName() {
		return patientFullName;
	}
	public void setPatientFullName(String patientFullName) {
		this.patientFullName = patientFullName;
	}
	public long getTestId() {
		return testId;
	}
	public void setTestId(long testId) {
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
		return "TestsPostRequest [patientId=" + patientId + ", patientMobileNo=" + patientMobileNo
				+ ", patientFullName=" + patientFullName + ", testId=" + testId + ", testName=" + testName
				+ ", scanReports=" + scanReports + ", bills=" + bills + "]";
	}
}
