package com.spring.bo;

import java.util.List;

import com.spring.exceptions.DoctorEmptyException;
import com.spring.exceptions.InValidNumericalException;
import com.spring.exceptions.InValidStringException;
import com.spring.pojo.Doctor;
import com.spring.pojo.Laboratory;

public interface IDoctorService {
	
	public List<Doctor> findAll();
	public boolean deleteDoctor(long id) throws InValidNumericalException;
	public Doctor updateDoctor(Doctor dcotor) throws DoctorEmptyException;
	public Doctor saveDoctor(Doctor doctor) throws DoctorEmptyException;
	public Doctor findByDoctorId(long doctorId) throws InValidNumericalException;
	public Doctor findByPhoneNumber(String phoneNumber) throws InValidStringException;
	public List<Doctor> findBySpecilization(String specilization) throws InValidStringException;
	public Doctor findByEmailId(String emailId) throws InValidStringException;
}
