package com.spring.pojo;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.GeneratorType;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.lang.Nullable;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Patient")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Patient implements Serializable {
	
	private static final long serialVersionUID = -2877151427988384201L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long patientId;
	
	@NotNull
	private String firstName;
	
	@NotNull
	private String lastName;
	
	@NotNull
	private String disease;
	
	@NotNull
	@Column(unique = true)
	private String mobileNumber;

	public long getPatientId() {
		return patientId;
	}

	public void setPatientId(long patientId) {
		this.patientId = patientId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getDisease() {
		return disease;
	}

	public void setDisease(String disease) {
		this.disease = disease;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	@Override
	public String toString() {
		return "Patient [patientId=" + patientId + ", firstName=" + firstName + ", lastName=" + lastName + ", disease="
				+ disease + ", mobileNumber=" + mobileNumber + "]";
	}

	public Patient(long patientId, @NotNull String firstName, @NotNull String lastName, @NotNull String disease,
			@NotNull String mobileNumber) {
		this.patientId = patientId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.disease = disease;
		this.mobileNumber = mobileNumber;
	}
	
	public Patient() {
		
	}
	
	
	
}
