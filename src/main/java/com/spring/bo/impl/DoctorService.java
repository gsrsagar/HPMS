package com.spring.bo.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.bo.IDoctorService;
import com.spring.dao.DoctorRepository;
import com.spring.exceptions.DoctorEmptyException;
import com.spring.exceptions.InValidNumericalException;
import com.spring.exceptions.InValidStringException;
import com.spring.pojo.Doctor;
import com.spring.util.Validator;


@Service
public class DoctorService implements IDoctorService {

	@Autowired
	DoctorRepository doctorRepository;
	
	@Override
	public List<Doctor> findAll() {
		return doctorRepository.findAll();
	}

	@Override
	public boolean deleteDoctor(long id) throws InValidNumericalException {
		if(!Validator.isValidNumerical(id)) {
			return doctorRepository.deleteById(id);
		} else {
			throw new InValidNumericalException("Invalid doctor id , pelase provide valid id to delete");
		}
	}

	@Override
	public Doctor updateDoctor(Doctor doctor) throws DoctorEmptyException {
		if(!(Validator.isEmptyDoctor(doctor) && Validator.isValidNumerical(doctor.getDoctorId()))) {
			return doctorRepository.save(doctor);
		}
		else {
			throw new DoctorEmptyException("invalid doctor details , please provide valid data");
		}
	}

	@Override
	public Doctor saveDoctor(Doctor doctor) throws DoctorEmptyException {
		if(!Validator.isEmptyDoctor(doctor)) {
			return doctorRepository.save(doctor);
		}
		else {
			throw new DoctorEmptyException("invalid doctor details , please provide valid data");
		}
	}

	@Override
	public Doctor findByDoctorId(long doctorId) throws InValidNumericalException {
		if(!Validator.isValidNumerical(doctorId)) {
			return doctorRepository.findByDoctorId(doctorId);
		}
		else {
			throw new InValidNumericalException("invalid doctor Id , please provide valid doctor Id");
		}
	}

	@Override
	public Doctor findByPhoneNumber(String phoneNumber) throws InValidStringException {
		if(!Validator.isValidString(phoneNumber)) {
			return doctorRepository.findByPhoneNumber(phoneNumber);
		}
		else {
			throw new InValidStringException("invalid doctor Id , please provide valid doctor Id");
		}
	}

	@Override
	public List<Doctor> findBySpecilization(String specilization) throws InValidStringException {
		if(!Validator.isValidString(specilization)) {
			return doctorRepository.findBySpecilization(specilization);
		}
		else {
			throw new InValidStringException("invalid doctor specilization , please provide valid specilization");
		}
	}

	@Override
	public Doctor findByEmailId(String emailId) throws InValidStringException {
		if(!Validator.isValidString(emailId)) {
			return doctorRepository.findByEmail(emailId);
		}
		else {
			throw new InValidStringException("invalid doctor emailid , please provide valid emailid");
		}
	}

}
