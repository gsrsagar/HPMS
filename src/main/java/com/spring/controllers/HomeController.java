package com.spring.controllers;


import java.util.HashMap;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.constants.ResponseCode;
import com.spring.constants.WebConstants;
import com.spring.dao.DoctorRepository;
import com.spring.dao.LaboratoryRepository;
import com.spring.dao.UserRepository;
import com.spring.pojo.User;
import com.spring.response.serverResp;
import com.spring.util.JwtUtil;
import com.spring.util.Validator;
import com.spring.viewModel.LoginViewModel;

import io.swagger.annotations.Api;

@CrossOrigin(origins = WebConstants.ALLOWED_URL)
@RestController
@RequestMapping("api/home")
@Api(value = "API to Maintain the Hospital Data by different inputs of Data",
description = "This API provides the capability to Add , Delete or modify the hospital Data", produces = "application/json")
public class HomeController {

	@Autowired
	private UserRepository userRepo;

	@Autowired
	private DoctorRepository doctorRepo;
	
	@Autowired
	private LaboratoryRepository labRepo;
	
	@Autowired
	private JwtUtil jwtutil;
	
	@PostMapping("/registerUser")
	public ResponseEntity<serverResp> addDoctor(@RequestBody User user) {
		serverResp resp = new serverResp();
		if (Validator.isUserEmpty(user)) {
			resp.setStatus(ResponseCode.BAD_REQUEST_CODE);
			resp.setMessage(ResponseCode.BAD_REQUEST_MESSAGE);
		} else {
			try {
					user.setUsertype(WebConstants.USER_PATIENT);
					User  result = userRepo.saveAndFlush(user);
					String jwtToken = jwtutil.createToken(user.getEmail(), user.getPassword(), WebConstants.USER_PATIENT);
					resp.setStatus(ResponseCode.SUCCESS_CODE);
					resp.setMessage(ResponseCode.SUCCESS_MESSAGE);
					resp.setAUTH_TOKEN(jwtToken);
					resp.setEmail(user.getEmail());
					resp.setUserName(result.getUsername());
					resp.setUserType(result.getUsertype());		
			} catch (Exception e) {
					resp.setStatus(ResponseCode.FAILURE_CODE);
					resp.setMessage(e.getMessage());
					return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(resp);
			}
		}
		
		return ResponseEntity.status(HttpStatus.CREATED).body(resp);
	}
	
	
	
	@PostMapping("/login") 
	public ResponseEntity<serverResp> verifyUser(@Valid @RequestBody HashMap<String, String> credential) {
		String email = "";
		String password = "";
		if (credential.containsKey(WebConstants.USER_EMAIL)) {
			email = credential.get(WebConstants.USER_EMAIL);
		}
		if (credential.containsKey(WebConstants.USER_PASSWORD)) {
			password = credential.get(WebConstants.USER_PASSWORD);
		}
		User loggedUser = userRepo.findByEmailAndPassword(email, password);
		serverResp resp = new serverResp();
		if (loggedUser != null) {
			String jwtToken = jwtutil.createToken(email, password, loggedUser.getUsertype());
			resp.setStatus(ResponseCode.SUCCESS_CODE);
			resp.setMessage(ResponseCode.SUCCESS_MESSAGE);
			resp.setAUTH_TOKEN(jwtToken);
			resp.setEmail(loggedUser.getEmail());
			resp.setUserName(loggedUser.getUsername());
			resp.setUserType(loggedUser.getUsertype());	
		} else {
			resp.setStatus(ResponseCode.FAILURE_CODE);
			resp.setMessage(ResponseCode.FAILURE_MESSAGE);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(resp);
		}
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(resp);
	}
}
