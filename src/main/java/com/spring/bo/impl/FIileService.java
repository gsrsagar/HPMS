package com.spring.bo.impl;

import java.io.IOException;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.spring.bo.IFileStorageService;
import com.spring.dao.FileRepository;
import com.spring.exceptions.InValidNumericalException;
import com.spring.pojo.Files;
import com.spring.util.Validator;

@Service
public class FIileService implements IFileStorageService {

	@Autowired
	FileRepository fileRepository;
	
	@Override
	public Files store(MultipartFile file) throws IOException {
		String fileName = StringUtils.cleanPath(file.getOriginalFilename());
			Files FileDB = new Files(fileName, file.getContentType(), file.getBytes());

		    return fileRepository.save(FileDB);
	}

	@Override
	public Files getFile(long id) throws Exception, InValidNumericalException {
		if(!Validator.isValidNumerical(id)) {
			try {
				Files result = fileRepository.findById(id);
				return result;
			}	
			catch(Exception e) {
				throw new Exception(e.getMessage());
			}
		}
		else {
			throw new InValidNumericalException("invalid provided id  , please provide valid input");
		}
	}

	@Override
	public Stream<Files> getAllFiles() throws Exception, IOException {
		try {
			return fileRepository.findAll().stream();
		}
		catch(Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	@Override
	public boolean deleteFile(long id) throws Exception, InValidNumericalException {
		if(Validator.isValidNumerical(id)) {
			try {
				 return fileRepository.deleteById(id);
			  }catch(Exception e) {
				 throw new Exception(e.getMessage());
			  }
		}
		else {
			throw new InValidNumericalException("Invalid id of the file , please provide valid id");
		}
		 
	  }

}
