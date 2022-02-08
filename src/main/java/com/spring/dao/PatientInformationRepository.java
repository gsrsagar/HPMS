package com.spring.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spring.exceptions.InValidNumericalException;
import com.spring.exceptions.InValidStringException;
import com.spring.exceptions.PatientEmptyException;
import com.spring.pojo.Doctor;
import com.spring.pojo.Patient;
import com.spring.pojo.PatientInformation;


@Repository
@Transactional
public interface PatientInformationRepository extends JpaRepository<PatientInformation, Long> {
	PatientInformation findByEmail(String email);
	//List<PatientInformation> findByFullName(String fullName);
	List<PatientInformation> findByDoctor(Doctor doctor);
	//PatientInformation findByMobileNumber(String mobileNumber);
	PatientInformation findByPatient(Patient patient);
	void deleteByPatient(Patient patient) throws PatientEmptyException;
	void deleteById(long Id) throws InValidNumericalException;
	void deleteByEmail(String email) throws InValidStringException;
	
	
}
