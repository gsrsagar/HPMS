package com.spring.pojo;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Doctor")
@AllArgsConstructor
@NoArgsConstructor
public class Doctor implements Serializable {

	private static final long serialVersionUID = -7446162716367847201L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long doctorId;
	private String name;
	private int age;
	private String gender;
	private String specilization;
	private long costPerAppointment;
	private String email;
	private String phoneNumber;
	
	
	public long getDoctorId() {
		return doctorId;
	}
	public void setDoctorId(long doctorId) {
		this.doctorId = doctorId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getSpecilization() {
		return specilization;
	}
	public void setSpecilization(String specilization) {
		this.specilization = specilization;
	}
	public long getCostPerAppointment() {
		return costPerAppointment;
	}
	public void setCostPerAppointment(long costPerAppointment) {
		this.costPerAppointment = costPerAppointment;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	@Override
	public String toString() {
		return "Doctor [doctorId=" + doctorId + ", name=" + name + ", age=" + age + ", gender=" + gender
				+ ", specilization=" + specilization + ", costPerAppointment=" + costPerAppointment + ", email=" + email
				+ ", phoneNumber=" + phoneNumber + "]";
	}
	
	public Doctor() {}
	public Doctor(long doctorId, String name, int age, String gender, String specilization, long costPerAppointment,
			String email, String phoneNumber) {
		super();
		this.doctorId = doctorId;
		this.name = name;
		this.age = age;
		this.gender = gender;
		this.specilization = specilization;
		this.costPerAppointment = costPerAppointment;
		this.email = email;
		this.phoneNumber = phoneNumber;
	}
	
	
	
	
}