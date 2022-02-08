package com.spring.bo;

import java.util.List;

import com.spring.exceptions.InValidNumericalException;
import com.spring.exceptions.InValidStringException;
import com.spring.exceptions.PatientEmptyException;
import com.spring.pojo.Patient;
import com.spring.pojo.PatientInformation;

public interface IPatientService {
	
	public Patient savePatient(Patient patient) throws PatientEmptyException;
	public Patient updatePatient(Patient patient) throws PatientEmptyException;
	public List<Patient> findAll();
	public Patient findByPatientId(long patientId) throws InValidNumericalException;
	public Patient findByMobileNumber(String mobileNumber) throws InValidStringException;
	public List<Patient> findByDisease(String disease) throws InValidStringException;
	public List<Patient> findByFirstName(String firstName) throws InValidStringException;
	public List<Patient> findByLastName(String lastName) throws InValidStringException;
	public List<Patient> findByFirstNameAndLastName(String firstName, String lastName) throws InValidStringException; 
	public void deleteByPatientId(long patientId) throws InValidNumericalException;
	public void deleteByMobileNumber(String mobileNumber)throws InValidStringException;
	public void deleteByFirstNameAndLastName(String firstName, String lastname)throws InValidStringException;
}
