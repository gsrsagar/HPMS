package com.spring.controllers;

import java.io.IOException;
import java.util.HashMap;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.spring.bo.IDoctorService;
import com.spring.bo.ILaboratoryService;
import com.spring.bo.IPatientService;
import com.spring.bo.ITestsService;
import com.spring.bo.IUserService;
import com.spring.constants.ResponseCode;
import com.spring.constants.WebConstants;
import com.spring.exceptions.InValidStringException;
import com.spring.pojo.Doctor;
import com.spring.pojo.Laboratory;
import com.spring.pojo.Tests;
import com.spring.pojo.User;
import com.spring.response.ResponseMessage;
import com.spring.response.doctorResp;
import com.spring.response.labResp;
import com.spring.response.serverResp;
import com.spring.response.testResp;
import com.spring.util.JwtUtil;
import com.spring.util.Validator;

import io.swagger.annotations.Api;

@CrossOrigin(origins = WebConstants.ALLOWED_URL)
@RestController
@RequestMapping("api/admin")
@Api(value = "API to Maintain the Hospital Data by different inputs of Data",
description = "This API provides the capability to Add , Delete or modify the hospital Data", produces = "application/json")
public class AdminController {

	@Autowired
	private IUserService userService;

	@Autowired
	private IDoctorService doctorService;
	
	@Autowired
	private ILaboratoryService labService;

	@Autowired
	private ITestsService testService;
	
	@Autowired
	private IPatientService patientService;
	
	@Autowired
	private JwtUtil jwtutil;
	
	private final static Logger logger = LoggerFactory.getLogger(AdminController.class);

	@PostMapping("/addDoctor")
	public ResponseEntity<doctorResp> addDoctor(@Valid @RequestBody Doctor doctor,
			@RequestHeader(name = WebConstants.USER_AUTH_TOKEN) String AUTH_TOKEN) {
		doctorResp resp = new doctorResp();
		if (Validator.isEmptyDoctor(doctor)) {
			resp.setStatus(ResponseCode.BAD_REQUEST_CODE);
			resp.setMessage(ResponseCode.BAD_REQUEST_MESSAGE);
		} else if (!Validator.isValidString(AUTH_TOKEN) && jwtutil.checkToken(AUTH_TOKEN,WebConstants.USER_ADMIN_ROLE) != null) {
			try {
				//User user = jwtutil.checkToken(AUTH_TOKEN,WebConstants.USER_DOCTOR);
				Doctor person = new Doctor();
				person.setName(doctor.getName());
				person.setGender(doctor.getGender());
				person.setAge(doctor.getAge());
				person.setSpecilization(doctor.getSpecilization());
				person.setCostPerAppointment(doctor.getCostPerAppointment());
				person.setEmail(doctor.getEmail());
				person.setPhoneNumber(doctor.getPhoneNumber());
				Doctor doct = doctorService.saveDoctor(person);
				resp.setDoctorlist(doctorService.findAll());
				resp.setStatus(ResponseCode.SUCCESS_CODE);
				resp.setMessage(ResponseCode.DOCT_REG);
				resp.setAUTH_TOKEN(AUTH_TOKEN);
			} catch (Exception e) {
				resp.setStatus(ResponseCode.FAILURE_CODE);
				resp.setMessage(e.getMessage());
				resp.setAUTH_TOKEN(AUTH_TOKEN);
			}
		} else {
			resp.setStatus(ResponseCode.FAILURE_CODE);
			resp.setMessage(ResponseCode.FAILURE_MESSAGE);
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(null);
		}
		return ResponseEntity.status(HttpStatus.CREATED).body(resp);
	}
	
	@PutMapping("/updateDoctor")
	public ResponseEntity<serverResp> updateDoctor(
			@RequestHeader(name = WebConstants.USER_AUTH_TOKEN) String AUTH_TOKEN,
			@Valid @RequestBody Doctor doctor) throws IOException {

		serverResp resp = new serverResp();
		if (Validator.isEmptyDoctor(doctor)) {
			resp.setStatus(ResponseCode.BAD_REQUEST_CODE);
			resp.setMessage(ResponseCode.BAD_REQUEST_MESSAGE);
		} else if (!Validator.isValidString(AUTH_TOKEN) && jwtutil.checkToken(AUTH_TOKEN,WebConstants.USER_ADMIN_ROLE) != null) {
			try {
				Doctor doc = doctorService.findByDoctorId(doctor.getDoctorId());
				doc.setAge(doctor.getAge());
				doc.setGender(doctor.getGender());
				doc.setName(doctor.getName());
				doc.setSpecilization(doctor.getSpecilization());
				doc.setCostPerAppointment(doctor.getCostPerAppointment());
				doc.setEmail(doctor.getEmail());
				doc.setPhoneNumber(doctor.getPhoneNumber());
				doctorService.saveDoctor(doc);
				resp.setStatus(ResponseCode.SUCCESS_CODE);
				resp.setMessage(ResponseCode.UPD_DOCTOR_SUCCESS_MESSAGE);
				resp.setAUTH_TOKEN(AUTH_TOKEN);
			} catch (Exception e) {
				resp.setStatus(ResponseCode.FAILURE_CODE);
				resp.setMessage(e.toString());
				resp.setAUTH_TOKEN(AUTH_TOKEN);
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(resp);
			}
		} else {
			resp.setStatus(ResponseCode.FAILURE_CODE);
			resp.setMessage(ResponseCode.FAILURE_MESSAGE);
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(resp);
		}
		return ResponseEntity.status(HttpStatus.CREATED).body(resp);
	}
	
	@DeleteMapping("/delDoctor")
	public ResponseEntity<doctorResp> deleteDoctor(@RequestHeader(name = WebConstants.USER_AUTH_TOKEN) String AUTH_TOKEN,
			@RequestParam(name = WebConstants.DOCTOR_ID) int doctorId) throws IOException {
		doctorResp resp =  new doctorResp();
		if (Validator.isValidNumerical(doctorId)) {
			resp.setStatus(ResponseCode.BAD_REQUEST_CODE);
			resp.setMessage(ResponseCode.BAD_REQUEST_MESSAGE); 
		} else if (!Validator.isValidString(AUTH_TOKEN) && jwtutil.checkToken(AUTH_TOKEN,WebConstants.USER_ADMIN_ROLE) != null) {
			try {
				doctorService.deleteDoctor(doctorId);
				resp.setStatus(ResponseCode.SUCCESS_CODE);
				resp.setMessage(ResponseCode.DEL_SUCCESS_MESSAGE);
				resp.setAUTH_TOKEN(AUTH_TOKEN);
				resp.setDoctorlist(doctorService.findAll());
			} catch (Exception e) {
				resp.setStatus(ResponseCode.FAILURE_CODE);
				resp.setMessage(e.toString());
				resp.setAUTH_TOKEN(AUTH_TOKEN);
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(resp);
			}
		} else {
			resp.setStatus(ResponseCode.FAILURE_CODE);
			resp.setMessage(ResponseCode.FAILURE_MESSAGE);
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(resp);
		}
		return ResponseEntity.status(HttpStatus.OK).body(resp);
	}
	
	@GetMapping("/getDoctors")
	public ResponseEntity<doctorResp> getDoctors(@RequestHeader(name = WebConstants.USER_AUTH_TOKEN) String AUTH_TOKEN)
			throws IOException {
		doctorResp resp = new doctorResp();
		if (!Validator.isValidString(AUTH_TOKEN) && jwtutil.checkToken(AUTH_TOKEN,WebConstants.USER_ADMIN_ROLE) != null) {
			try {
				resp.setStatus(ResponseCode.SUCCESS_CODE);
				resp.setMessage(ResponseCode.DOCTOR_SUCCESS_MESSAGE);
				resp.setAUTH_TOKEN(AUTH_TOKEN);
				resp.setDoctorlist(doctorService.findAll());
			} catch (Exception e) {
				resp.setStatus(ResponseCode.FAILURE_CODE);
				resp.setMessage(e.getMessage());
				resp.setAUTH_TOKEN(AUTH_TOKEN);
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(resp);
			}
		} else {
			resp.setStatus(ResponseCode.FAILURE_CODE);
			resp.setMessage(ResponseCode.FAILURE_MESSAGE);
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(resp);
		}
		return ResponseEntity.status(HttpStatus.OK).body(resp);
	}
	
	@PostMapping("/addLaboratory")
	public ResponseEntity<labResp> addLaboratory(@Valid @RequestBody Laboratory laboratory,
			@RequestHeader(name = WebConstants.USER_AUTH_TOKEN) String AUTH_TOKEN) throws IOException {
		labResp resp = new labResp();
		if(Validator.isLabEmpty(laboratory)) {
			resp.setStatus(ResponseCode.BAD_REQUEST_CODE);
			resp.setMessage(ResponseCode.BAD_REQUEST_MESSAGE);
		}
		else if(!Validator.isValidString(AUTH_TOKEN) && jwtutil.checkToken(AUTH_TOKEN,WebConstants.USER_ADMIN_ROLE) != null) {
			try {
				laboratory.setLabName(laboratory.getLabName().toString().toLowerCase());
				Laboratory lab = labService.saveLab(laboratory);
				resp.setLaboratoryList(labService.findAll());
				resp.setMessage(ResponseCode.SUCCESS_CODE);
				resp.setStatus(ResponseCode.SUCCESS_MESSAGE);
				resp.setAUTH_TOKEN(AUTH_TOKEN);
				return ResponseEntity.status(HttpStatus.OK).body(resp);
			} 
			catch (Exception e) {
				resp.setStatus(ResponseCode.FAILURE_CODE);
				resp.setMessage(e.getMessage());
				resp.setAUTH_TOKEN(AUTH_TOKEN);
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(resp);
			}
		}
		return ResponseEntity.status(HttpStatus.OK).body(resp);
	}
	
	@DeleteMapping("/deleteLaboratory")
	public ResponseEntity<doctorResp> deleteLab(@RequestHeader(name = WebConstants.USER_AUTH_TOKEN) String AUTH_TOKEN,
			@RequestParam(name = WebConstants.LAB_ID) String labId) throws IOException {
		doctorResp resp =  new doctorResp();
		if (Validator.isValidString((labId))) {
			resp.setStatus(ResponseCode.BAD_REQUEST_CODE);
			resp.setMessage(ResponseCode.BAD_REQUEST_MESSAGE);
		} else if (!Validator.isValidString(AUTH_TOKEN) && jwtutil.checkToken(AUTH_TOKEN,WebConstants.USER_ADMIN_ROLE) != null) {
			try {
				//labService.(labId);
				resp.setStatus(ResponseCode.SUCCESS_CODE);
				resp.setMessage(ResponseCode.DEL_SUCCESS_MESSAGE);
				resp.setAUTH_TOKEN(AUTH_TOKEN);
				resp.setDoctorlist(doctorService.findAll());
			} catch (Exception e) {
				resp.setStatus(ResponseCode.FAILURE_CODE);
				resp.setMessage(e.toString());
				resp.setAUTH_TOKEN(AUTH_TOKEN);
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(resp);
			}
		} else {
			resp.setStatus(ResponseCode.FAILURE_CODE);
			resp.setMessage(ResponseCode.FAILURE_MESSAGE);
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(resp);
		}
		return ResponseEntity.status(HttpStatus.OK).body(resp);
	}
	
	@PutMapping("/updateLaboratory")
	public ResponseEntity<serverResp> updateLaboratory(
			@RequestHeader(name = WebConstants.USER_AUTH_TOKEN) String AUTH_TOKEN,
			@Valid @RequestBody Laboratory laboratory) throws IOException {

		serverResp resp = new serverResp();
		if (Validator.isLabEmpty(laboratory)) {
			resp.setStatus(ResponseCode.BAD_REQUEST_CODE);
			resp.setMessage(ResponseCode.BAD_REQUEST_MESSAGE);
		} else if (!Validator.isValidString(AUTH_TOKEN) && jwtutil.checkToken(AUTH_TOKEN,WebConstants.USER_ADMIN_ROLE) != null) {
			try {
				Laboratory lab = labService.findByLabId(laboratory.getLabId());
				lab.setLabName(laboratory.getLabName());
				labService.saveLab(lab);
				resp.setStatus(ResponseCode.SUCCESS_CODE);
				resp.setMessage(ResponseCode.LAB_UPD);
				resp.setAUTH_TOKEN(AUTH_TOKEN);
			} catch (Exception e) {
				resp.setStatus(ResponseCode.FAILURE_CODE);
				resp.setMessage(e.toString());
				resp.setAUTH_TOKEN(AUTH_TOKEN);
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(resp);
			}
		} else {
			resp.setStatus(ResponseCode.FAILURE_CODE);
			resp.setMessage(ResponseCode.FAILURE_MESSAGE);
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(resp);
		}
		return ResponseEntity.status(HttpStatus.OK).body(resp);
	}
	
	@GetMapping("/getAllLabaratories")
	public ResponseEntity<labResp> getLaboratories(@RequestHeader(name = WebConstants.USER_AUTH_TOKEN) String AUTH_TOKEN)
			throws IOException {
		labResp resp = new labResp();
		if (!Validator.isValidString(AUTH_TOKEN) && jwtutil.checkToken(AUTH_TOKEN,WebConstants.USER_ADMIN_ROLE) != null) {
			try {
				resp.setStatus(ResponseCode.SUCCESS_CODE);
				resp.setMessage(ResponseCode.LAB_SUCCESS_MESSAGE);
				resp.setAUTH_TOKEN(AUTH_TOKEN);
				resp.setLaboratoryList(labService.findAll());
				patientService.findAll().forEach((e)->System.out.println("Value"+e));
			} catch (Exception e) {
				resp.setStatus(ResponseCode.FAILURE_CODE);
				resp.setMessage(e.getMessage());
				resp.setAUTH_TOKEN(AUTH_TOKEN);
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(resp);
			}
		} else {
			resp.setStatus(ResponseCode.FAILURE_CODE);
			resp.setMessage(ResponseCode.FAILURE_MESSAGE);
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(resp);
		}
		return ResponseEntity.status(HttpStatus.OK).body(resp);
	}
	
	@PostMapping("/addTests")
	public ResponseEntity<testResp> addTest(@Valid @RequestBody Tests test,
			@RequestHeader(name = WebConstants.USER_AUTH_TOKEN) String AUTH_TOKEN) throws IOException {
		testResp resp = new testResp();
		if(Validator.isTestsEmpty(test)) {
			resp.setStatus(ResponseCode.BAD_REQUEST_CODE);
			resp.setMessage(ResponseCode.BAD_REQUEST_MESSAGE);
		}
		else if(!Validator.isValidString(AUTH_TOKEN) && jwtutil.checkToken(AUTH_TOKEN,WebConstants.USER_ADMIN_ROLE) != null) {
			try {
				Tests result = testService.saveTest(test);
				resp.setTestList(testService.findAll());
				resp.setMessage(ResponseCode.SUCCESS_CODE);
				resp.setStatus(ResponseCode.SUCCESS_MESSAGE);
				resp.setAUTH_TOKEN(AUTH_TOKEN);
			} 
			catch (Exception e) {
				resp.setStatus(ResponseCode.FAILURE_CODE);
				resp.setMessage(e.getMessage());
				resp.setAUTH_TOKEN(AUTH_TOKEN);
				return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(resp);
			}
		}
		return ResponseEntity.status(HttpStatus.OK).body(resp);
	}
	
	@PutMapping("/updateTest")
	public ResponseEntity<testResp> updateTest(
			@RequestHeader(name = WebConstants.USER_AUTH_TOKEN) String AUTH_TOKEN,
			@Valid @RequestBody Tests test) throws IOException {
		
		testResp resp = new testResp();
		if (Validator.isTestsEmpty(test)) {
			resp.setStatus(ResponseCode.BAD_REQUEST_CODE);
			resp.setMessage(ResponseCode.BAD_REQUEST_MESSAGE);
		} else if (!Validator.isValidString(AUTH_TOKEN) && jwtutil.checkToken(AUTH_TOKEN,WebConstants.USER_ADMIN_ROLE) != null) {
			try {
				Tests result = testService.findByTestId(test.getTestId());
				result.setCost(test.getCost());
				result.setTestName(test.getTestName());
				result.setLab(test.getLab());
				testService.saveTest(result);
				resp.setStatus(ResponseCode.SUCCESS_CODE);
				resp.setMessage(ResponseCode.UPD_DOCTOR_SUCCESS_MESSAGE);
				resp.setAUTH_TOKEN(AUTH_TOKEN);
			} catch (Exception e) {
				resp.setStatus(ResponseCode.FAILURE_CODE);
				resp.setMessage(e.toString());
				resp.setAUTH_TOKEN(AUTH_TOKEN);
			}
		} else {
			resp.setStatus(ResponseCode.FAILURE_CODE);
			resp.setMessage(ResponseCode.FAILURE_MESSAGE);
		}
		return ResponseEntity.status(HttpStatus.OK).body(resp);
	}
	
	
	@GetMapping("/getAllTests")
	public ResponseEntity<testResp> getTests(@RequestHeader(name = WebConstants.USER_AUTH_TOKEN) String AUTH_TOKEN)
			throws IOException {
		testResp resp = new testResp();
		if (!Validator.isValidString(AUTH_TOKEN) && jwtutil.checkToken(AUTH_TOKEN,WebConstants.USER_ADMIN_ROLE) != null) {
			try {
				resp.setTestList(testService.findAll());
				resp.setStatus(ResponseCode.SUCCESS_CODE);
				resp.setMessage(ResponseCode.TESTS_SUCCESS_MESSAGE);
				resp.setAUTH_TOKEN(AUTH_TOKEN);
			} catch (Exception e) {
				resp.setStatus(ResponseCode.FAILURE_CODE);
				resp.setMessage(e.getMessage());
				resp.setAUTH_TOKEN(AUTH_TOKEN);
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(resp);
			}
		} else {
			resp.setStatus(ResponseCode.FAILURE_CODE);
			resp.setMessage(ResponseCode.FAILURE_MESSAGE);
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(resp);
		}
		return ResponseEntity.status(HttpStatus.OK).body(resp);
	}
	
	@DeleteMapping("/deleteTest")
	public ResponseEntity<testResp> deleteTest(@RequestHeader(name = WebConstants.USER_AUTH_TOKEN) String AUTH_TOKEN,
			@RequestParam(name = WebConstants.TEST_ID) long testId) throws IOException {
		testResp resp =  new testResp();
		if (Validator.isValidNumerical((testId))) {
			resp.setStatus(ResponseCode.BAD_REQUEST_CODE);
			resp.setMessage(ResponseCode.BAD_REQUEST_MESSAGE);
		} else if (!Validator.isValidString(AUTH_TOKEN) && jwtutil.checkToken(AUTH_TOKEN,WebConstants.USER_ADMIN_ROLE) != null) {
			try {
				testService.deleteByTestId(testId);
				resp.setStatus(ResponseCode.SUCCESS_CODE);
				resp.setMessage(ResponseCode.DEL_SUCCESS_MESSAGE);
				resp.setAUTH_TOKEN(AUTH_TOKEN);
				resp.setTestList(testService.findAll());
			} catch (Exception e) {
				resp.setStatus(ResponseCode.FAILURE_CODE);
				resp.setMessage(e.toString());
				resp.setAUTH_TOKEN(AUTH_TOKEN);
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(resp);
			}
		} else {
			resp.setStatus(ResponseCode.FAILURE_CODE);
			resp.setMessage(ResponseCode.FAILURE_MESSAGE);
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(resp);
		}
		return ResponseEntity.status(HttpStatus.OK).body(resp);
	}
	
	@PostMapping("/login") 
	public ResponseEntity<serverResp> verifyUser(@Valid @RequestBody HashMap<String, String> credential) {
		String email = "";
		String password = "";
		serverResp resp = new serverResp();
		if (credential.containsKey(WebConstants.USER_EMAIL)) {
			email = credential.get(WebConstants.USER_EMAIL);
		}
		if (credential.containsKey(WebConstants.USER_PASSWORD)) {
			password = credential.get(WebConstants.USER_PASSWORD);
		}
		User loggedUser;
		try {
			loggedUser = userService.findByEmailAndPasswordAndUsertype(email, password, WebConstants.USER_ADMIN_ROLE);
		} catch (InValidStringException e) {
			resp.setMessage(ResponseCode.FAILURE_MESSAGE);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(resp);
		}
		if (loggedUser != null) {
			String jwtToken = jwtutil.createToken(email, password, WebConstants.USER_ADMIN_ROLE);
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
		return ResponseEntity.status(HttpStatus.OK).body(resp);
	}
	
	@PostMapping("/registerStaff")
	public ResponseEntity<serverResp> registerStaff(@Valid @RequestBody User user,
			@RequestHeader(name = WebConstants.USER_AUTH_TOKEN) String AUTH_TOKEN) {
		serverResp resp = new serverResp();
		if (Validator.isUserEmpty(user)) {
			resp.setStatus(ResponseCode.BAD_REQUEST_CODE);
			resp.setMessage(ResponseCode.BAD_REQUEST_MESSAGE);
		} else if(!Validator.isValidString(AUTH_TOKEN) && jwtutil.checkToken(AUTH_TOKEN,WebConstants.USER_ADMIN_ROLE) != null){
			try {
					User result = userService.saveUser(user);
					resp.setStatus(ResponseCode.SUCCESS_CODE);
					resp.setMessage(ResponseCode.SUCCESS_MESSAGE);
					resp.setAUTH_TOKEN(AUTH_TOKEN);
					resp.setEmail(null);
					resp.setUserName(null);
					resp.setUserType(WebConstants.USER_ADMIN_ROLE);		
			} catch (Exception e) {
					resp.setStatus(ResponseCode.FAILURE_CODE);
					resp.setMessage(e.getMessage());
					return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(resp);
			}
		}
		else {
			resp.setStatus(ResponseCode.BAD_REQUEST_CODE);
			resp.setMessage(ResponseCode.BAD_REQUEST_MESSAGE);
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(resp);
		}
		
		return ResponseEntity.status(HttpStatus.OK).body(resp);
	}

}
