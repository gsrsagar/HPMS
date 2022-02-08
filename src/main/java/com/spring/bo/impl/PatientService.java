package com.spring.bo.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.bo.IPatientService;
import com.spring.dao.PatientRepository;
import com.spring.exceptions.InValidNumericalException;
import com.spring.exceptions.InValidStringException;
import com.spring.exceptions.PatientEmptyException;
import com.spring.pojo.Patient;
import com.spring.util.Validator;


@Service
public class PatientService implements IPatientService {

	@Autowired
	PatientRepository  patientRepository;
	
	@Override
	public Patient savePatient(Patient patient) throws PatientEmptyException {
		if(!Validator.isEmptyPatient(patient)) {
			Patient result = patientRepository.save(patient);
			return result;
		}
		else {
			throw new PatientEmptyException("Patient data is invalid , please provide valid data");
		}
	}

	@Override
	public Patient updatePatient(Patient patient) throws PatientEmptyException {
		if(!Validator.isEmptyPatient(patient)) {
			Patient result = patientRepository.save(patient);
			return result;
		}
		else {
			throw new PatientEmptyException("Patient data is invalid , please provide valid data to update patient details");
		}
	}

	@Override
	public List<Patient> findAll() {
		return patientRepository.findAll();
	}

	@Override
	public Patient findByPatientId(long patientId) throws InValidNumericalException{
		if(!Validator.isValidNumerical(patientId)) {
			Patient result = patientRepository.findByPatientId(patientId);
			return result;
		}
		else {
			throw new InValidNumericalException("Invalid input, please provide valid patient id to fetch data");
		}
	}

	@Override
	public Patient findByMobileNumber(String mobileNumber) throws InValidStringException {
		if(!Validator.isValidString(mobileNumber)) {
			Patient result = patientRepository.findByMobileNumber(mobileNumber);
			return result;
		}
		else {
			throw new InValidStringException("Invalid mobile number, please provide valid input to fetch data");
		}
	}

	@Override
	public List<Patient> findByDisease(String disease) throws InValidStringException {
		if(!Validator.isValidString(disease)) {
			List<Patient> result = patientRepository.findByDisease(disease);
			return result;
		}
		else {
			throw new InValidStringException("Invalid input , please provide valid input to fetch data");
		}
	}

	@Override
	public List<Patient> findByFirstName(String firstName) throws InValidStringException {
		if(!(Validator.isValidString(firstName))) {
			List<Patient> result = patientRepository.findByFirstName(firstName);
			return result;
		}
		else {
			throw new InValidStringException("Invalid firstname, please provide valid input to fetch data");
		}
	}

	@Override
	public List<Patient> findByLastName(String lastName) throws InValidStringException {
		if(!(Validator.isValidString(lastName))) {
			List<Patient> result = patientRepository.findByLastName(lastName);
			return result;
		}
		else {
			throw new InValidStringException("Invalid last name, please provide valid input to fetch data");
		}
	}

	@Override
	public List<Patient> findByFirstNameAndLastName(String firstName, String lastName) throws InValidStringException {
		if(!(Validator.isValidString(lastName) && Validator.isValidString(firstName))) {
			List<Patient> result = patientRepository.findByFirstNameAndLastName(firstName, lastName);
			return result;
		}
		else {
			throw new InValidStringException("Invalid last name and first name , please provide valid input to fetch data");
		}
	}

	@Override
	public void deleteByPatientId(long patientId) throws InValidNumericalException {
		if(!Validator.isValidNumerical(patientId)) {
			 patientRepository.findByPatientId(patientId);
		}
		else {
			throw new InValidNumericalException("Invalid input, please provide valid patient id to fetch data");
		}
		
	}

	@Override
	public void deleteByMobileNumber(String mobileNumber) throws InValidStringException {
		if(!(Validator.isValidString(mobileNumber))) {
			patientRepository.deleteByMobileNumber(mobileNumber);
		}
		else {
			throw new InValidStringException("Invalid mobile number , please provide valid input to fetch data");
		}
	}

	@Override
	public void deleteByFirstNameAndLastName(String firstName, String lastname) throws InValidStringException {
		if(!(Validator.isValidString(lastname) && Validator.isValidString(firstName))) {
			patientRepository.deleteByFirstNameAndLastName(firstName, lastname);
		}
		else {
			throw new InValidStringException("Invalid last name and first name , please provide valid input to fetch data");
		}
		
	}

}
