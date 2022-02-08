package com.spring.util;

import com.spring.pojo.Address;
import com.spring.pojo.Doctor;
import com.spring.pojo.Laboratory;
import com.spring.pojo.Patient;
import com.spring.pojo.PatientInformation;
import com.spring.pojo.Tests;
import com.spring.pojo.User;
import com.spring.viewModel.PatientFilterBody;
import com.spring.viewModel.TestsPostRequest;

public class Validator {

	
	public static boolean isUserEmpty(User user) {
		if (user.getAge() == 0) {
			return true;
		}
		if (user.getPassword() == null || user.getPassword() == "") {
			return true;
		}
		if (user.getEmail() == null || user.getEmail() == "") {
			return true;
		}
		if (user.getUsername() == null || user.getUsername() == "") {
			return true;
		}
		return false;
	}
	
	public static boolean isTestsEmpty(Tests test) {
		if(test.getTestName().isEmpty()) {
			return true;
		}
		else if(test.getCost()<=0) {
			return true;
		}
		else if(test.getTestName().isEmpty()) {
			return true;
		}
		return false;
	}
	
	public static boolean isEmptyPatient(Patient patient) {
		if(patient.getMobileNumber() == null || patient.getMobileNumber() == "") {
			return true;
		}
		else if(patient.getDisease() == null || patient.getDisease() == "") {
			return true;
		}
		else if(patient.getFirstName() == null || patient.getFirstName() == "") {
			return true;
		}
		else if(patient.getLastName() == null || patient.getLastName() == "") {
			return true;
		}
		else {
			return false;
		}
	}
	
	public static boolean isInValidPatientFilterBody(PatientFilterBody body) {
		if((body.getPatientId() <=0) &&
		    (body.getEmail() == null || body.getEmail() == "") && 
		    ((body.getFirstName() == null || body.getFirstName() == "") &&
		    (body.getLastName() == null || body.getLastName() == "")) &&
		    (body.getMobileNumber() == null || body.getMobileNumber() == "")) {
			return true;
		}
		else return false;
	}
	
	public static boolean isEmptyDoctor(Doctor doctor) {
		if (doctor.getName() == null || doctor.getName() == "") {
			return true;
		}
		if (doctor.getAge() == 0) {
			return true;
		}
		if (doctor.getGender() == null || doctor.getGender() == "") {
			return true;
		}
		if (doctor.getSpecilization() == null || doctor.getSpecilization() == "") {
			return true;
		}
		return false;
	}
	
	
	public static boolean isEmptyTestPostRequest(TestsPostRequest testPostRequest) {
		if(testPostRequest.getPatientId()<=0) {
			return true;
		}
		else if(testPostRequest.getPatientMobileNo()=="" || testPostRequest.getPatientMobileNo()==null) {
			return true;
		}
		else if(testPostRequest.getTestId()<=0) {
			return true;
		}
		else if(testPostRequest.getTestName()==null || testPostRequest.getTestName()=="") {
			return true;
		}
		else if(testPostRequest.getScanReports().size()<=0) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public static boolean isEmptyPatientInformation(PatientInformation patientInformation) {
		if(patientInformation.getDisease().isEmpty()) {
			return true;
		}
		else if(patientInformation.getDoctor()!=null) {
			return true;
		}
		else if(patientInformation.getEmail().isEmpty()) {
			return true;
		}
		return false;
	}
	
	public static boolean isEmptyAddress(Address address) {
		if (address.getAddress() == null || address.getAddress() == "") {
			return true;
		}
		if (address.getCity() == null || address.getCity() == "") {
			return true;
		}
		if (address.getState() == null || address.getState() == "") {
			return true;
		}
		if (address.getCountry() == null || address.getCountry() == "") {
			return true;
		}
		if (address.getPhoneNumber() == null || address.getPhoneNumber() == "") {
			return true;
		}
		if (address.getZipcode() == 0) {
			return true;
		}
		return false;
	}
	
	
	public static boolean isLabEmpty(Laboratory lab) {
		if(lab.getLabName() == null || lab.getLabName() =="") {
			return true;
		}
		return false;
	}
	
	public static boolean isValidNumerical(long input) {
		if(input == 0 || input<0) {
			return false;
		}
		return true;
	}
	
	public static boolean isValidString(String input) {
		if (input != null && input != "") {
			if (input.matches("[a-zA-Z0-9]*")) {
				return false;
			}
		}
		return true;
	}
	
}
