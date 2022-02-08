package com.spring.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spring.pojo.Doctor;

@Repository
@Transactional
public interface DoctorRepository extends JpaRepository<Doctor, Long> {
	Doctor findByDoctorId(long doctorId);
	Doctor findByPhoneNumber(String phoneNumber);
	List<Doctor> findBySpecilization(String specilization);
	Doctor findByEmail(String email);
	boolean deleteById(long doctorId);
	
}