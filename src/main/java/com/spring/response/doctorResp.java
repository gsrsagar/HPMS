package com.spring.response;

import java.io.Serializable;
import java.util.List;

import com.spring.pojo.Doctor;


public class doctorResp implements Serializable {

	private static final long serialVersionUID = 4744643015194204171L;
	private String status;
	private String message;
	private String AUTH_TOKEN;
	private List<Doctor> doctorlist;

	public List<Doctor> getDoctorlist() {
		return doctorlist;
	}

	
	public String getAUTH_TOKEN() {
		return AUTH_TOKEN;
	}

	public void setAUTH_TOKEN(String aUTH_TOKEN) {
		this.AUTH_TOKEN = aUTH_TOKEN;
	}

	private Doctor doctor;

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

	public Doctor getDoctor() {
		return doctor;
	}

	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}

	public void setDoctorlist(List<Doctor> doctorlist) {
		this.doctorlist = doctorlist;
		
	}

}