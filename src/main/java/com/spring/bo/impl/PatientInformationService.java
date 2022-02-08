package com.spring.bo.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.bo.IPatientInformationService;
import com.spring.dao.PatientInformationRepository;
import com.spring.exceptions.DoctorEmptyException;
import com.spring.exceptions.InValidNumericalException;
import com.spring.exceptions.InValidStringException;
import com.spring.exceptions.PatientEmptyException;
import com.spring.exceptions.PatientInformationEmptyException;
import com.spring.pojo.Doctor;
import com.spring.pojo.Patient;
import com.spring.pojo.PatientInformation;
import com.spring.pojo.Tests;
import com.spring.util.Validator;

@Service
public class PatientInformationService implements IPatientInformationService {

	@Autowired
	PatientInformationRepository patientInformationRepository;
	
	@Override
	public List<PatientInformation> findAll() {
		return this.patientInformationRepository.findAll();
	}

	@Override
	public List<PatientInformation> findByDoctor(Doctor doctor) throws DoctorEmptyException {
		if(!Validator.isEmptyDoctor(doctor)) {
			List<PatientInformation> result = patientInformationRepository.findByDoctor(doctor);
			return result;
		}
		else {
			throw new DoctorEmptyException("Invalid doctor data , Please provide valid doctor data");
		}
	}

	@Override
	public PatientInformation updatePatientInfo(PatientInformation patientInfo) throws PatientInformationEmptyException {
		if(!Validator.isEmptyPatientInformation(patientInfo)) {
			PatientInformation result = patientInformationRepository.save(patientInfo);
			return result;
		}
		else {
			throw new PatientInformationEmptyException("Invalid patient info data ,"
					+ " Please provide valid patient information data");
		}
	}

	@Override
	public PatientInformation savePatientInfo(PatientInformation patientInfo)  throws PatientInformationEmptyException {
		if(!Validator.isEmptyPatientInformation(patientInfo)) {
			PatientInformation result = patientInformationRepository.save(patientInfo);
			return result;
		}
		else {
			throw new PatientInformationEmptyException("Invalid patient info data , Please provide"
					+ " valid patient information data");
		}
	}

	@Override
	public PatientInformation findByEmail(String email) throws InValidStringException {
		if(!Validator.isValidString(email)) {
			PatientInformation result = patientInformationRepository.findByEmail(email);
			return result;
		}
		else {
			throw new InValidStringException("Invalid email id  , Please provide"
					+ " valid email id");
		}
	}

	@Override
	public List<PatientInformation> findByFullName(String fullName) throws InValidStringException {
		if(!Validator.isValidString(fullName)) {
			//List<PatientInformation> result = patientInformationRepository.findByFullName(fullName);
			return new ArrayList<PatientInformation>();
		}
		else {
			throw new InValidStringException("Invalid full name , Please provide"
					+ " valid full name");
		}
	}


	@Override
	public PatientInformation findByMobileNumber(String mobileNumber) throws InValidStringException {
		if(!Validator.isValidString(mobileNumber)) {
			//PatientInformation result = patientInformationRepository.findByMobileNumber(mobileNumber);
			return new PatientInformation();
		}
		else {
			throw new InValidStringException("Invalid mobile number, Please provide"
					+ " valid mobile number");
		}
	}

	@Override
	public List<PatientInformation> findAll(Doctor doctor) throws DoctorEmptyException {
		if(!Validator.isEmptyDoctor(doctor)) {
			List<PatientInformation> result = patientInformationRepository.findByDoctor(doctor);
			return result;
		}
		else {
			throw new DoctorEmptyException("Invalid mobile number, Please provide"
					+ " valid mobile number");
		}
	}

	@Override
	public PatientInformation findByPatient(Patient patient) throws PatientEmptyException {
		if(!Validator.isEmptyPatient(patient)) {
			PatientInformation result = patientInformationRepository.findByPatient(patient);
			return result;
		}
		else {
			throw new PatientEmptyException("Invalid Patient details, Please provide"
					+ " valid data");
		}
	}

	@Override
	public void deleteByPatient(Patient patient) throws PatientEmptyException {
		if(!Validator.isEmptyPatient(patient)) {
			patientInformationRepository.findByPatient(patient);
		}
		else {
			throw new PatientEmptyException("Invalid Patient details, Please provide"
					+ " valid data");
		}
	}

	@Override
	public void deleteById(long Id) throws InValidNumericalException {
		if(!Validator.isValidNumerical(Id)) {
			patientInformationRepository.deleteById(Id);
		}
		else {
			throw new InValidNumericalException("Invalid Patient Information Id , Please provide"
					+ " valid data");
		}
		
	}

	@Override
	public void deleteByEmail(String email) throws InValidStringException {
		if(!Validator.isValidString(email)) {
			patientInformationRepository.deleteByEmail(email);
		}
		else {
			throw new InValidStringException("Invalid Patient Email Id , Please provide"
					+ " valid data");
		}
		
	}

}
