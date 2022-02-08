package com.spring.bo;

import java.util.List;

import com.spring.exceptions.InValidNumericalException;
import com.spring.exceptions.InValidStringException;
import com.spring.exceptions.LabEmptyException;
import com.spring.pojo.Laboratory;

public interface ILaboratoryService {
	
	public List<Laboratory> findAll();
	public Laboratory updateLab(Laboratory laboratory) throws LabEmptyException;
	public Laboratory saveLab(Laboratory laboraotry) throws LabEmptyException;
	public Laboratory findByLabId(long labId) throws InValidNumericalException;
	public List<Laboratory> findByLabName(String labName) throws InValidStringException;
}


