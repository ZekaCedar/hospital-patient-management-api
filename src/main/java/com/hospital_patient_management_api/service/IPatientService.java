package com.hospital_patient_management_api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.hospital_patient_management_api.model.Patient;
import com.hospital_patient_management_api.repository.PatientRepository;

@Service
public class IPatientService implements PatientService{
	
	@Autowired
	PatientRepository patientRepository;

	@Override
	public Patient addPatient(Patient patient) {
		// TODO Auto-generated method stub
		return patientRepository.save(patient);
	}

	@Override
	public Patient getPatient(Long id) {
		// TODO Auto-generated method stub
		return patientRepository.findById(id)
		        .orElseThrow(() -> new RuntimeException("Patient not found with id: " + id));
	}

	@Override
	public Patient updatePatient(Patient patient, Long id) {
		// TODO Auto-generated method stub
		Patient toUpdate = patientRepository.findById(id)
		        .orElseThrow(() -> new RuntimeException("Patient not found with id: " + id));
			
		toUpdate.setFirstName(patient.getFirstName());
		toUpdate.setLastName(patient.getLastName());
		toUpdate.setDateOfBirth(patient.getDateOfBirth());
		toUpdate.setPhoneNumber(patient.getPhoneNumber());
		toUpdate.setEmail(patient.getEmail());
		toUpdate.setAddress(patient.getAddress());
		toUpdate.setEmergencyContact(patient.getEmergencyContact());
		toUpdate.setBloodType(patient.getBloodType());
		toUpdate.setIsActive(patient.getIsActive());
			
		return patientRepository.save(toUpdate);
	
	
	}

	@Override
	public Patient deletePatient(Long id) {
		// TODO Auto-generated method stub
		Patient toDelete = patientRepository.findById(id)
		        .orElseThrow(() -> new RuntimeException("Patient not found with id: " + id));
		
		toDelete.setIsActive(false);
		
		return patientRepository.save(toDelete);
	}

	@Override
	public Page<Patient> getAllPatient(int page, int size) {
		// TODO Auto-generated method stub
		Pageable pageable = PageRequest.of(page, size);
		return patientRepository.findAll(pageable);
	}


}