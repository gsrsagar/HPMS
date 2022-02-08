package com.spring.dao;


import java.util.List;

import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.pojo.Laboratory;
import com.spring.pojo.Tests;

public interface TestRepository extends JpaRepository<Tests, Long> {
	Tests findByTestId(long testId);
	List<Tests> findByTestName(String testName);
	List<Tests> findByLab(Laboratory lab);
	void deleteByTestId(long testId);
}