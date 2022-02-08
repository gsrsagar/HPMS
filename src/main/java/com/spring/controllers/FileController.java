package com.spring.controllers;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.spring.bo.IFileStorageService;
import com.spring.constants.WebConstants;
import com.spring.exceptions.InValidNumericalException;
import com.spring.pojo.Files;
import com.spring.response.ResponseFile;
import com.spring.response.ResponseMessage;
import com.spring.util.JwtUtil;
import com.spring.util.Validator;

import io.swagger.annotations.Api;

@RestController
@RequestMapping("api/attachments")
@CrossOrigin(origins=WebConstants.ALLOWED_URL)
@Api(value = "API to Maintain the Reports of th Patients",
description = "This API provides the capability to Add , Delete or modify the hospital patients reports", produces = "application/json")
public class FileController {

  @Autowired
  private IFileStorageService storageService;
  
  @Autowired
  private JwtUtil jwtutil;

  @PostMapping("/uploadFile")
  public ResponseEntity<ResponseMessage> uploadFile(@RequestHeader(name = WebConstants.USER_AUTH_TOKEN) String AUTH_TOKEN,
		  @RequestParam("file") MultipartFile file) {
    String message = "";
    if (!Validator.isValidString(AUTH_TOKEN) && jwtutil.checkToken(AUTH_TOKEN, null) != null) {
	    try {
	      Files f= storageService.store(file);
	      message = "Uploaded the file successfully: " + file.getOriginalFilename();
	      return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message,f));
	    } catch (Exception e) {
	      message = "Could not upload the file: " + file.getOriginalFilename() + "!";
	      return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message,null));
	    }
    }
    else {
    	return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage("Invalid Authentication",null));
    }
  }

  @GetMapping("/getAllfiles")
  public ResponseEntity<List<ResponseFile>> getListFiles(@RequestHeader(name = WebConstants.USER_AUTH_TOKEN) String AUTH_TOKEN) {
	  List<ResponseFile> files = null;
	  if (!Validator.isValidString(AUTH_TOKEN) && jwtutil.checkToken(AUTH_TOKEN, null) != null) {
		  try {
			files = storageService.getAllFiles().map(dbFile -> {
			  String fileDownloadUri = ServletUriComponentsBuilder
			      .fromCurrentContextPath()
			      .path("/attachments/files/")
			      .path(String.valueOf((dbFile.getId())))
			      .toUriString();

			  return new ResponseFile(
			      dbFile.getName(),
			      fileDownloadUri,
			      dbFile.getType(),
			      dbFile.getData().length);
			}).collect(Collectors.toList());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	    return ResponseEntity.status(HttpStatus.OK).body(files);
	  }
	  else {
		  return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(files);
	  }
  }

  @GetMapping("/files/{id}")
  public ResponseEntity<byte[]> getFile(@RequestHeader(name = WebConstants.USER_AUTH_TOKEN) String AUTH_TOKEN,
		  @PathVariable long id) {
	  Files fileDB = null;
	  if (!Validator.isValidString(AUTH_TOKEN) && jwtutil.checkToken(AUTH_TOKEN, null) != null) {
		  try {
			fileDB = storageService.getFile(id);
		} catch (InValidNumericalException e) {
			return ResponseEntity.internalServerError()
			        .header(HttpHeaders.PROXY_AUTHORIZATION, null)
			        .body(null);
		} catch (Exception e) {
			return ResponseEntity.internalServerError()
			        .header(HttpHeaders.PROXY_AUTHORIZATION, null)
			        .body(null);
		}
		  return ResponseEntity.ok()
	        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileDB.getName() + "\"")
	        .body(fileDB.getData());
	  }
	  else {
		  return ResponseEntity.internalServerError()
			        .header(HttpHeaders.PROXY_AUTHORIZATION, null)
			        .body(null);
	  }
  }
  
}
