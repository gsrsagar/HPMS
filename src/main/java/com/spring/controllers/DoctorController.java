package com.spring.controllers;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.bo.IDoctorService;
import com.spring.bo.ILaboratoryService;
import com.spring.bo.IPatientInformationService;
import com.spring.bo.IPatientService;
import com.spring.bo.ITestsService;
import com.spring.bo.IUserService;
import com.spring.constants.ResponseCode;
import com.spring.constants.WebConstants;
import com.spring.exceptions.PatientEmptyException;
import com.spring.pojo.Patient;
import com.spring.pojo.PatientInformation;
import com.spring.response.getAllPatientsResponse;
import com.spring.response.patientResp;
import com.spring.util.JwtUtil;
import com.spring.util.Validator;
import com.spring.viewModel.PatientAndPatientInformationBody;
import com.spring.viewModel.PatientPrescription;
import com.spring.viewModel.PatientTests;

import io.swagger.annotations.Api;


@CrossOrigin(origins = WebConstants.ALLOWED_URL)
@RestController
@RequestMapping("api/doctor")
@Api(value = "API to Maintain the Hospital Data by different inputs of Data",
description = "This API provides the capability to Add , Delete or modify the hospital Data", produces = "application/json")
public class DoctorController {
	
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
	private IPatientInformationService patientInfoService;
	
	@Autowired
	private JwtUtil jwtutil;
	
	
	@GetMapping("/{doctorId}/patients")
	public ResponseEntity<patientResp> getPatients(@RequestHeader(name = WebConstants.USER_AUTH_TOKEN) String AUTH_TOKEN,
			 @PathVariable String doctorId)
			throws IOException {
		patientResp resp = new patientResp();
		if (!Validator.isValidString(AUTH_TOKEN) && jwtutil.checkToken(AUTH_TOKEN, WebConstants.USER_DOCTOR) != null) {
			try {
				resp.setStatus(ResponseCode.SUCCESS_CODE);
				resp.setMessage(ResponseCode.PATIENT_SUCCESS_MESSAGE);
				resp.setAUTH_TOKEN(AUTH_TOKEN);
				resp.setPatientList(patientService.findAll());
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
	
	@PostMapping("/{doctorId}/prescription")
	public ResponseEntity<patientResp> savePrescription(@RequestHeader(name = WebConstants.USER_AUTH_TOKEN) String AUTH_TOKEN,
			 @PathVariable String doctorId, @RequestBody PatientPrescription patientPrescription)
			throws IOException {
		patientResp resp = new patientResp();
		if (!Validator.isValidString(AUTH_TOKEN) && jwtutil.checkToken(AUTH_TOKEN,WebConstants.USER_DOCTOR) != null) {
			if(!(Validator.isValidString(patientPrescription.getPrescription()) && Validator.isValidNumerical(patientPrescription.getPatientId()))) {
					try {
							Patient patient =patientService.findByPatientId(patientPrescription.getPatientId());
							PatientInformation patientInfo = patientInfoService.findByPatient(patient);
							patient.setPatientId(patientPrescription.getPatientId());
							patientInfo.setDoctorPrescription(patientPrescription.getPrescription());
							patientService.savePatient(patient);
							patientInfoService.savePatientInfo(patientInfo);
							resp.setStatus(ResponseCode.SUCCESS_CODE);
							resp.setMessage(ResponseCode.PATIENT_SUCCESS_MESSAGE);
							resp.setAUTH_TOKEN(AUTH_TOKEN);
							resp.setPatientList(patientService.findAll());
					} catch (Exception e) {
						resp.setStatus(ResponseCode.FAILURE_CODE);
						resp.setMessage(e.getMessage());
						resp.setAUTH_TOKEN(AUTH_TOKEN);
						return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(resp);
					}
				}
			} else {
				resp.setStatus(ResponseCode.FAILURE_CODE);
				resp.setMessage(ResponseCode.FAILURE_MESSAGE);
				return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(resp);
			}
		return ResponseEntity.status(HttpStatus.OK).body(resp);
	}
	
	@PostMapping("/{doctorId}/tests")
	public ResponseEntity<patientResp> saveTests(@RequestHeader(name = WebConstants.USER_AUTH_TOKEN) String AUTH_TOKEN,
			 @PathVariable String doctorId, @RequestBody PatientTests patientTests)
			throws IOException {
		patientResp resp = new patientResp();
		if (!Validator.isValidString(AUTH_TOKEN) && jwtutil.checkToken(AUTH_TOKEN,WebConstants.USER_DOCTOR) != null) {
			if(!Validator.isValidNumerical(patientTests.getPatientId())) {
					try {
							Patient patient =patientService.findByPatientId(patientTests.getPatientId());
							PatientInformation patientInfo = patientInfoService.findByPatient(patient);
							patientInfo.setTests(patientTests.getTests());
							patientService.savePatient(patient);
							patientInfoService.savePatientInfo(patientInfo);
							resp.setStatus(ResponseCode.SUCCESS_CODE);
							resp.setMessage(ResponseCode.PATIENT_SUCCESS_MESSAGE);
							resp.setAUTH_TOKEN(AUTH_TOKEN);
							resp.setPatientList(patientService.findAll());
					} catch (Exception e) {
						resp.setStatus(ResponseCode.FAILURE_CODE);
						resp.setMessage(e.getMessage());
						resp.setAUTH_TOKEN(AUTH_TOKEN);
						return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(resp);
					}
				}
			} else {
				resp.setStatus(ResponseCode.FAILURE_CODE);
				resp.setMessage(ResponseCode.FAILURE_MESSAGE);
				return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(resp);
			}
		return ResponseEntity.status(HttpStatus.OK).body(resp);
	}
	
	@GetMapping("/getAllPatients")
	public ResponseEntity<getAllPatientsResponse> getPatientsInformation(@RequestHeader(name = WebConstants.USER_AUTH_TOKEN) String AUTH_TOKEN)
			throws IOException {
		getAllPatientsResponse resp = new getAllPatientsResponse();
		if (!Validator.isValidString(AUTH_TOKEN) && jwtutil.checkToken(AUTH_TOKEN,WebConstants.USER_RECEPTIONIST) != null) {
			try {
				List<PatientAndPatientInformationBody> patientResultList = null;
				List<Patient> patients = patientService.findAll();
				patients.forEach(patient->{
					PatientAndPatientInformationBody patientInformationBody = new PatientAndPatientInformationBody();
					patientInformationBody.setPatient(patient);
					try {
						patientInformationBody.setPatientInformation(patientInfoService.findByPatient(patient));
					} catch (PatientEmptyException e) {
						// TODO Auto-generated catch block
						resp.setStatus(ResponseCode.FAILURE_CODE);
						resp.setMessage(e.getMessage());
						resp.setAUTH_TOKEN(AUTH_TOKEN);
						return;
					}
					patientResultList.add(patientInformationBody);
					
				});
				resp.setPatientInfoBody(patientResultList);
				resp.setStatus(ResponseCode.SUCCESS_CODE);
				resp.setMessage(ResponseCode.SUCCESS_MESSAGE);
				resp.setAUTH_TOKEN(AUTH_TOKEN);
			} catch (Exception e) {
				resp.setStatus(ResponseCode.FAILURE_CODE);
				resp.setMessage(e.getMessage());
				resp.setAUTH_TOKEN(AUTH_TOKEN);
				return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(resp);
			}
		} else {
			resp.setStatus(ResponseCode.FAILURE_CODE);
			resp.setMessage(ResponseCode.FAILURE_MESSAGE);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(resp);
		}
		return ResponseEntity.status(HttpStatus.OK).body(resp);
	}
	
}
