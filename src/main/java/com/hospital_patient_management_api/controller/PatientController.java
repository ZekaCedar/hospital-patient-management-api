package com.hospital_patient_management_api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hospital_patient_management_api.model.Patient;
import com.hospital_patient_management_api.service.PatientService;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
public class PatientController{
	
	@Autowired
	PatientService patientService;
	
	@PostMapping("/patients")
	public ResponseEntity<?> addPatient(@Valid @RequestBody Patient patient){
        return new ResponseEntity<>(patientService.addPatient(patient), HttpStatus.CREATED);
	}
	
	@GetMapping("/patients/{id}")
	public ResponseEntity<?> getPatient(@PathVariable Long id){
        return new ResponseEntity<>(patientService.getPatient(id), HttpStatus.FOUND);
	}
	
	@PutMapping("/patients/{id}")
	public ResponseEntity<?> updatePatient(@Valid @RequestBody Patient patient, @PathVariable Long id){
        return new ResponseEntity<>(patientService.updatePatient(patient, id), HttpStatus.OK);
	}
	
	@DeleteMapping("/patients/{id}")
	public ResponseEntity<?> softDeletePatient(@PathVariable Long id){
		return new ResponseEntity<>(patientService.deletePatient(id), HttpStatus.OK);
	}
	
	@GetMapping("/patients")
	public ResponseEntity<?> getAllPatient(
			@RequestParam(defaultValue = "0") int page,
	        @RequestParam(defaultValue = "10") int size
			){
		return new ResponseEntity<>(patientService.getAllPatient(page, size), HttpStatus.OK);
	}
	
}