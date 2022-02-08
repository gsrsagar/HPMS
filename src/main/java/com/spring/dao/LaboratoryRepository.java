package com.spring.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.pojo.Laboratory;

public interface LaboratoryRepository extends JpaRepository<Laboratory, Long>{
	Laboratory findByLabId(long labId);
	List<Laboratory> findByLabName(String labName);
}
