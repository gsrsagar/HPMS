package com.spring.bo;

import java.io.File;
import java.io.IOException;
import java.util.stream.Stream;

import org.springframework.web.multipart.MultipartFile;

import com.spring.exceptions.InValidNumericalException;
import com.spring.pojo.Files;

public interface IFileStorageService {
	
	public Files store(MultipartFile file) throws IOException;
	public Files getFile(long id) throws Exception, InValidNumericalException;
	public Stream<Files> getAllFiles() throws Exception, IOException;
	public boolean deleteFile(long id) throws Exception, InValidNumericalException;
}
