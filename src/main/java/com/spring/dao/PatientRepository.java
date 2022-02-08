package com.spring.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spring.pojo.Patient;

@Repository
@Transactional
public interface PatientRepository extends JpaRepository<Patient, Long> {
	Patient findByPatientId(long patientId);
	Patient findByMobileNumber(String mobileNumber);
	List<Patient> findByDisease(String disease);
	List<Patient> findByFirstName(String firstName); // select * from user where firstname='sagar';
	List<Patient> findByLastName(String lastName);
	List<Patient> findByFirstNameAndLastName(String firstName, String lastName);  
	void deleteByPatientId(long patientId);
	void deleteByMobileNumber(String mobileNumber);
	void deleteByFirstNameAndLastName(String firstName, String lastname);
}