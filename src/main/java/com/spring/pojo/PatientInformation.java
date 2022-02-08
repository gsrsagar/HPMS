package com.spring.pojo;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.springframework.lang.Nullable;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "PatientInformation")
@AllArgsConstructor
@NoArgsConstructor
public class PatientInformation {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Nullable
	@Column(unique = true)
	private String email;
	
	private String gender;
	
	@NotNull
	private String disease;
	
	@OneToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "doctorId", nullable = false, unique = false)
	private Doctor doctor;
	
	@OneToOne(fetch = FetchType.LAZY, optional = true)
	@JoinColumn(name = "addressId", nullable = true, unique = false)
	private Address address;
	
	private String doctorPrescription;
	
	@ManyToMany(fetch = FetchType.LAZY, cascade =  CascadeType.MERGE)
	@Column(nullable=true)
	private List<Tests> tests;
	
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
	@Column(nullable =true)
	private List<Files> files;
	
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
	@Column(nullable = true)
	private List<Files> bills;
	
	@OneToOne
	@JoinColumn(name ="patient_id")
	private Patient patient;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getDisease() {
		return disease;
	}

	public void setDisease(String disease) {
		this.disease = disease;
	}

	public Doctor getDoctor() {
		return doctor;
	}

	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public String getDoctorPrescription() {
		return doctorPrescription;
	}

	public void setDoctorPrescription(String doctorPrescription) {
		this.doctorPrescription = doctorPrescription;
	}

	public List<Tests> getTests() {
		return tests;
	}

	public void setTests(List<Tests> tests) {
		this.tests = tests;
	}

	public List<Files> getFiles() {
		return files;
	}

	public void setFiles(List<Files> files) {
		this.files = files;
	}

	public List<Files> getBills() {
		return bills;
	}

	public void setBills(List<Files> bills) {
		this.bills = bills;
	}

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	public PatientInformation(long id, String email, String gender, @NotNull String disease, Doctor doctor,
			Address address, String doctorPrescription, List<Tests> tests, List<Files> files, List<Files> bills,
			Patient patient) {
		id = id;
		this.email = email;
		this.gender = gender;
		this.disease = disease;
		this.doctor = doctor;
		this.address = address;
		this.doctorPrescription = doctorPrescription;
		this.tests = tests;
		this.files = files;
		this.bills = bills;
		this.patient = patient;
	}

	public PatientInformation() {}

	@Override
	public String toString() {
		return "PatientInformation [Id=" + id + ", email=" + email + ", gender=" + gender + ", disease=" + disease
				+ ", doctor=" + doctor + ", address=" + address + ", doctorPrescription=" + doctorPrescription
				+ ", tests=" + tests + ", files=" + files + ", bills=" + bills + ", patient=" + patient + "]";
	}
	
	
	
}
