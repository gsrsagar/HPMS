package com.spring.controllers;

import java.io.IOException;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;
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
import com.spring.pojo.Patient;
import com.spring.pojo.PatientInformation;
import com.spring.response.radiologistGetResp;
import com.spring.response.testPostResponse;
import com.spring.util.JwtUtil;
import com.spring.util.Validator;
import com.spring.viewModel.GetTestPatientList;
import com.spring.viewModel.TestsPostRequest;

import io.swagger.annotations.Api;

@CrossOrigin(origins = WebConstants.ALLOWED_URL)
@RestController
@RequestMapping("api/radiology")
@Api(value = "API to Maintain the Hospital Data by different inputs of Data",
description = "This API provides the capability to Add , Delete or modify the hospital Data", produces = "application/json")
public class RadiologyController {
	
	@Autowired
	private IUserService userService;

	@Autowired
	private IDoctorService doctorService;
	
	@Autowired
	private ILaboratoryService labService;

	@Autowired
	private ITestsService testsService;
	
	@Autowired
	private IPatientInformationService patientInfoService;
	
	@Autowired
	private IPatientService patientService;
	
	@Autowired
	private JwtUtil jwtutil;
	
	@Autowired
	private SessionFactory sessionFactory;
	
	
	@GetMapping("/{id}/scans")
	public ResponseEntity<radiologistGetResp> getScans(@RequestHeader(name = WebConstants.USER_AUTH_TOKEN) String AUTH_TOKEN,
			 @PathVariable String id)
			throws IOException {
		radiologistGetResp resp = new radiologistGetResp();
		if (!Validator.isValidString(AUTH_TOKEN) && jwtutil.checkToken(AUTH_TOKEN,WebConstants.USER_RADIOLOGIST) != null && !Validator.isValidString(id)) {
			try {
				Session s = sessionFactory.openSession();
				Transaction tr= s.beginTransaction();
				String sql = "select p.patient_id, p.mobile_number, p.first_name, p.last_name,pt.tests_test_id,t.test_name from patient p join patient_tests pt join tests t join laboratory l"+
		                " "+"where l.lab_id=:lab_id AND t.lab_id=:lab_id AND pt.tests_test_id=t.test_id AND pt.patient_patient_id=p.patient_id";
				NativeQuery<GetTestPatientList> query = s.createSQLQuery(sql);
				query.setParameter("lab_id", id);
				List<GetTestPatientList> results = query.list();
					resp.setStatus(ResponseCode.SUCCESS_CODE);
					resp.setMessage(ResponseCode.TEST_LIST);
					resp.setAUTH_TOKEN(AUTH_TOKEN);
					resp.setTestList(results);
					tr.commit();
					s.close();
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
	
	@PostMapping("/{id}/scans")
	public ResponseEntity<testPostResponse> saveScanReport(@RequestHeader(name = WebConstants.USER_AUTH_TOKEN) String AUTH_TOKEN,
			@RequestBody TestsPostRequest scanReport)
			throws IOException {
		testPostResponse resp = new testPostResponse();
		if (!Validator.isValidString(AUTH_TOKEN) && jwtutil.checkToken(AUTH_TOKEN,WebConstants.USER_RADIOLOGIST) != null && !Validator.isEmptyTestPostRequest(scanReport)) {
			try {
					Patient patient = patientService.findByPatientId(scanReport.getPatientId());
					PatientInformation patientInfo = patientInfoService.findByPatient(patient);
					patientInfo.setBills(scanReport.getBills());
					patientInfo.setFiles(scanReport.getScanReports());
					patientService.savePatient(patient);
					resp.setStatus(ResponseCode.SUCCESS_CODE);
					resp.setMessage(ResponseCode.SCAN_REPORTS_SAVE);
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
}
