package com.hospital_patient_management_api.service;

import org.springframework.data.domain.Page;

import com.hospital_patient_management_api.model.Patient;

public interface PatientService {
	
	public Patient addPatient(Patient patient);
	
	public Patient getPatient(Long id);
	
	public Patient updatePatient(Patient patient, Long id);
	
	public Patient deletePatient(Long id);
	
	public Page<Patient> getAllPatient(int page, int size);

}