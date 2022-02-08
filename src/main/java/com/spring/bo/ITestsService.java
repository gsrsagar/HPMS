package com.spring.bo;

import java.util.List;

import com.spring.exceptions.InValidNumericalException;
import com.spring.exceptions.InValidStringException;
import com.spring.exceptions.LabEmptyException;
import com.spring.exceptions.TestsEmptyException;
import com.spring.pojo.Laboratory;
import com.spring.pojo.Tests;

public interface ITestsService {
	
	public Tests saveTest(Tests tests) throws TestsEmptyException;
	public Tests updateTest(Tests tests) throws TestsEmptyException;
	public List<Tests> findAll();
	public Tests findByTestId(long testId) throws InValidNumericalException;
	public List<Tests> findByTestName(String testName) throws InValidStringException;
	public List<Tests> findByLab(Laboratory lab) throws LabEmptyException;
	public void deleteByTestId(long testId) throws InValidNumericalException;
}
