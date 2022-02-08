package com.spring.bo.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.bo.ITestsService;
import com.spring.dao.TestRepository;
import com.spring.exceptions.InValidNumericalException;
import com.spring.exceptions.InValidStringException;
import com.spring.exceptions.LabEmptyException;
import com.spring.exceptions.TestsEmptyException;
import com.spring.pojo.Laboratory;
import com.spring.pojo.Tests;
import com.spring.util.Validator;

@Service
public class TestsService implements ITestsService {

	@Autowired
	TestRepository testRepository;
	
	@Override
	public Tests saveTest(Tests test) throws TestsEmptyException {
		if(!Validator.isTestsEmpty(test)) {
			Tests result = testRepository.save(test);
			return  result;
		}
		else {
			throw new TestsEmptyException("The given test body is empty , Please give valid data");
		}
	}

	@Override
	public Tests updateTest(Tests test) throws TestsEmptyException {
		if(!Validator.isTestsEmpty(test)) {
			Tests result = testRepository.save(test);
			return  result;
		}
		else {
			throw new TestsEmptyException("The given test body is empty , Please give valid data");
		}
	}

	@Override
	public List<Tests> findAll() {
		return this.testRepository.findAll();
	}

	@Override
	public Tests findByTestId(long testId) throws InValidNumericalException {
		if(!Validator.isValidNumerical(testId)) {
			Tests result = testRepository.findByTestId(testId);
			return result;
		}
		else {
			throw new InValidNumericalException("Invalid number , Please provide valid input");
		}
	}

	@Override
	public List<Tests> findByTestName(String testName) throws InValidStringException {
		if(!Validator.isValidString(testName)) {
			List<Tests> result = testRepository.findByTestName(testName);
			return result;
		}
		else {
			throw new InValidStringException("Invalid String , Please provide valid input");
		}
	}

	@Override
	public List<Tests> findByLab(Laboratory lab) throws LabEmptyException {
		if(!Validator.isLabEmpty(lab)) {
			List<Tests> result = testRepository.findByLab(lab);
			return result;
		}
		else {
			throw new LabEmptyException("Invalid Lab details , Please provide valid laboratory details");
		}
	}

	@Override
	public void deleteByTestId(long testId) throws InValidNumericalException {
		if(!Validator.isValidNumerical(testId)) {
			testRepository.deleteByTestId(testId);
		}
		else {
			throw new InValidNumericalException("Invalid tests id details , Please provide valid test id details");
		}
		
	}

}
