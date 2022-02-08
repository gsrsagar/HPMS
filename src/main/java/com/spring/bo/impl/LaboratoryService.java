package com.spring.bo.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.bo.ILaboratoryService;
import com.spring.dao.LaboratoryRepository;
import com.spring.exceptions.InValidNumericalException;
import com.spring.exceptions.InValidStringException;
import com.spring.exceptions.LabEmptyException;
import com.spring.pojo.Laboratory;
import com.spring.pojo.Patient;
import com.spring.pojo.PatientInformation;
import com.spring.util.Validator;

@Service
public class LaboratoryService implements ILaboratoryService {

	
	@Autowired
	LaboratoryRepository laboratoryRepository;
	
	@Override
	public List<Laboratory> findAll() {
		return laboratoryRepository.findAll();
	}

	@Override
	public Laboratory updateLab(Laboratory laboratory) throws LabEmptyException {
		if(!(Validator.isLabEmpty(laboratory) && Validator.isValidNumerical(laboratory.getLabId()))) {
			Laboratory result = laboratoryRepository.save(laboratory);
			return result;
		}
		else {
			throw new LabEmptyException("Invalid laboratory data, Please provide"
					+ " valid data");
		}
	}

	@Override
	public Laboratory saveLab(Laboratory laboraotry) throws LabEmptyException{
		if(!(Validator.isLabEmpty(laboraotry))) {
			Laboratory result = laboratoryRepository.save(laboraotry);
			return result;
		}
		else {
			throw new LabEmptyException("Invalid laboratory data, Please provide"
					+ " valid data");
		}
	}

	@Override
	public Laboratory findByLabId(long labId) throws InValidNumericalException {
		if(!(Validator.isValidNumerical(labId))) {
			Laboratory result = laboratoryRepository.findByLabId(labId);
			return result;
		}
		else {
			throw new InValidNumericalException("Invalid laboratory Id, please provide valid Id to fetch data");
		}
	}

	@Override
	public List<Laboratory> findByLabName(String labName) throws InValidStringException {
		if(!(Validator.isValidString(labName))) {
			List<Laboratory> result = laboratoryRepository.findByLabName(labName);
			return result;
		}
		else {
			throw new InValidStringException("Invalid laboratory name, please provide valid lab name to fetch data");
		}
	}

}
