package com.spring.bo;

import java.util.List;

import com.spring.exceptions.DoctorEmptyException;
import com.spring.exceptions.InValidNumericalException;
import com.spring.exceptions.InValidStringException;
import com.spring.exceptions.PatientEmptyException;
import com.spring.exceptions.PatientInformationEmptyException;
import com.spring.pojo.Doctor;
import com.spring.pojo.Patient;
import com.spring.pojo.PatientInformation;

public interface IPatientInformationService {
	
	public List<PatientInformation> findAll();
	public List<PatientInformation> findAll(Doctor doctor) throws DoctorEmptyException;
	public PatientInformation updatePatientInfo(PatientInformation patientInfo) throws PatientInformationEmptyException;
	public PatientInformation savePatientInfo(PatientInformation patientInfo)throws PatientInformationEmptyException;
	public PatientInformation findByEmail(String email) throws InValidStringException;
	public List<PatientInformation> findByFullName(String fullName) throws InValidStringException;
	public List<PatientInformation> findByDoctor(Doctor doctor) throws DoctorEmptyException;
	public PatientInformation findByMobileNumber(String mobileNumber) throws InValidStringException;
	public PatientInformation findByPatient(Patient patient) throws PatientEmptyException;
	public void deleteByPatient(Patient patient) throws PatientEmptyException;
	public void deleteById(long Id) throws InValidNumericalException;
	public void deleteByEmail(String email) throws InValidStringException;
}
