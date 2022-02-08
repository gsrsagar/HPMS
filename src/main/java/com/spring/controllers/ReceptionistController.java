package com.spring.controllers;

import java.io.IOException;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.bo.IAddressService;
import com.spring.bo.IDoctorService;
import com.spring.bo.IFileStorageService;
import com.spring.bo.ILaboratoryService;
import com.spring.bo.IPatientInformationService;
import com.spring.bo.IPatientService;
import com.spring.bo.ITestsService;
import com.spring.bo.IUserService;
import com.spring.constants.ResponseCode;
import com.spring.constants.WebConstants;
import com.spring.exceptions.PatientEmptyException;
import com.spring.pojo.Address;
import com.spring.pojo.Doctor;
import com.spring.pojo.Patient;
import com.spring.pojo.PatientInformation;
import com.spring.response.getAllPatientsResponse;
import com.spring.response.patientInfoResponse;
import com.spring.response.patientResp;
import com.spring.util.JwtUtil;
import com.spring.util.Validator;
import com.spring.viewModel.PatientAndPatientInformationBody;
import com.spring.viewModel.PatientFilterBody;

import io.swagger.annotations.Api;

@RestController
@RequestMapping("api/receptionist")
@CrossOrigin(origins = WebConstants.ALLOWED_URL)
@Api(value = "API to provide functionaly to the users once they are logged in ",
        description = "This API provides the operations for all the staff in hospital based on their roles", produces = "application/json")
public class ReceptionistController {

	//private static Logger logger = Logger.getLogger(ReceptionistController.class.getName());

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
	private IAddressService addressService;
	
	@Autowired
	private JwtUtil jwtutil;
	
	@Autowired
	private IFileStorageService fileStorageService;

	
	@PostMapping("/addPatient")
	public ResponseEntity<patientResp> addPatient(@Valid @RequestBody PatientAndPatientInformationBody patient,
			@RequestHeader(name = WebConstants.USER_AUTH_TOKEN) String AUTH_TOKEN) {
		patientResp resp = new patientResp();
		if (Validator.isEmptyPatient(patient.getPatient())) {
			resp.setStatus(ResponseCode.BAD_REQUEST_CODE);
			resp.setMessage(ResponseCode.BAD_REQUEST_MESSAGE);
		} else if (!Validator.isValidString(AUTH_TOKEN) && jwtutil.checkToken(AUTH_TOKEN,WebConstants.USER_RECEPTIONIST) != null) {
			try {
				if(patient.getPatientInformation().getAddress()!=null) {
					Address add = addressService.saveAddress(patient.getPatientInformation().getAddress());
					patient.getPatientInformation().setAddress(add);
				}
				patientService.savePatient(patient.getPatient());
				resp.setPatientList(patientService.findAll());
				resp.setStatus(ResponseCode.SUCCESS_CODE);
				resp.setMessage(ResponseCode.PATEINT_REG);
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
	
	@PutMapping("/updatePatient")
	public ResponseEntity<patientResp> updatePatient(@Valid @RequestBody PatientAndPatientInformationBody patientInfo,
			@RequestHeader(name = WebConstants.USER_AUTH_TOKEN) String AUTH_TOKEN) {
		patientResp resp = new patientResp();
		if (Validator.isEmptyPatient(patientInfo.getPatient())) {
			resp.setStatus(ResponseCode.BAD_REQUEST_CODE);
			resp.setMessage(ResponseCode.BAD_REQUEST_MESSAGE);
		} else if (!Validator.isValidString(AUTH_TOKEN) && jwtutil.checkToken(AUTH_TOKEN,WebConstants.USER_RECEPTIONIST) != null) {
			try {
				if(patientInfo.getPatientInformation().getAddress()!=null) {
					Address add = addressService.saveAddress(patientInfo.getPatientInformation().getAddress());
					patientInfo.getPatientInformation().setAddress(add);
				}
				if(patientInfo.getPatientInformation().getDoctor()!=null) {
					Doctor doct = doctorService.saveDoctor(patientInfo.getPatientInformation().getDoctor());
					patientInfo.getPatientInformation().setDoctor(doct);
				}
				patientService.savePatient(patientInfo.getPatient());
				resp.setPatientList(patientService.findAll());
				resp.setStatus(ResponseCode.SUCCESS_CODE);
				resp.setMessage(ResponseCode.PATIENT_UPD);
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
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(resp);
	}
	
	@PostMapping("/deletePatient")
	public ResponseEntity<patientResp> deletePatient(@RequestBody PatientFilterBody body,
			@RequestHeader(name = WebConstants.USER_AUTH_TOKEN) String AUTH_TOKEN) throws IOException {
		patientResp resp = new patientResp();
		if (Validator.isInValidPatientFilterBody(body)) {
			resp.setStatus(ResponseCode.BAD_REQUEST_CODE);
			resp.setMessage(ResponseCode.BAD_REQUEST_MESSAGE);
		} else if (!Validator.isValidString(AUTH_TOKEN) && jwtutil.checkToken(AUTH_TOKEN,WebConstants.USER_RECEPTIONIST) != null) {
			try {
				Patient result = new Patient();
				if(body.getPatientId()>0) {
					result = patientService.findByPatientId(body.getPatientId());
					patientService.deleteByPatientId(body.getPatientId());
					patientInfoService.deleteByPatient(result);
				}
				else if(body.getEmail()!=null || body.getEmail().trim()!="") {
					PatientInformation patientInformation =patientInfoService.findByEmail(body.getEmail());
					patientInfoService.deleteByEmail(body.getEmail());
					patientService.deleteByPatientId(patientInformation.getPatient().getPatientId());
				}
				else if((body.getFirstName() !=null || body.getFirstName().trim()!="") &&
						(body.getLastName() !=null || body.getLastName().trim()!="")) {
					List<Patient> patient = (patientService.findByFirstNameAndLastName(body.getFirstName(),body.getLastName()));
					patientInfoService.deleteByPatient(patient.get(0));
					patientService.deleteByFirstNameAndLastName(body.getFirstName(), body.getLastName());
					
				}
				else if(body.getMobileNumber()!=null || body.getMobileNumber()!="") {
					patientService.deleteByMobileNumber(body.getMobileNumber());
				}
				resp.setPatientList(patientService.findAll());
				resp.setStatus(ResponseCode.SUCCESS_CODE);
				resp.setMessage(ResponseCode.PATIENT_DEL);
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
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(resp);
	}
	
	@GetMapping("/getPatientsDetails")
	public ResponseEntity<patientResp> getPatients(@RequestHeader(name = WebConstants.USER_AUTH_TOKEN) String AUTH_TOKEN)
			throws IOException {
		patientResp resp = new patientResp();
		if (!Validator.isValidString(AUTH_TOKEN) && jwtutil.checkToken(AUTH_TOKEN,WebConstants.USER_RECEPTIONIST) != null) {
			try {
				resp.setPatientList(patientService.findAll());
				resp.setStatus(ResponseCode.SUCCESS_CODE);
				resp.setMessage(ResponseCode.SUCCESS_MESSAGE);
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
	
	@GetMapping("/getPatientsInformation")
	public ResponseEntity<patientInfoResponse> getPatientsInformation(@RequestHeader(name = WebConstants.USER_AUTH_TOKEN) String AUTH_TOKEN,
			 @PathVariable long id)
			throws IOException {
		patientInfoResponse resp = new patientInfoResponse();
		if (!Validator.isValidString(AUTH_TOKEN) && jwtutil.checkToken(AUTH_TOKEN,WebConstants.USER_RECEPTIONIST) != null) {
			try {
				Patient result = patientService.findByPatientId(id);
				resp.setPatientInfoList(patientInfoService.findByPatient(result));
				resp.setStatus(ResponseCode.SUCCESS_CODE);
				resp.setMessage(ResponseCode.SUCCESS_MESSAGE);
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
