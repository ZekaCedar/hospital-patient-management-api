package com.hospital_patient_management_api.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.hospital_patient_management_api.model.BloodType;
import com.hospital_patient_management_api.model.Patient;
import com.hospital_patient_management_api.repository.PatientRepository;

class PatientServiceTest{
	
	@Mock
	PatientRepository patientRepository;
	
	@InjectMocks
	IPatientService patientService;
	
	private Patient patient;
	
	@BeforeEach
	void setup() {
		MockitoAnnotations.openMocks(this);
		patient = new Patient();
		patient.setFirstName("John");
		patient.setLastName("Doe");
		patient.setDateOfBirth(LocalDate.of(1985,05,15));
		patient.setPhoneNumber("+1234567890");
		patient.setEmail("john.doe@email.com");
		patient.setAddress("123 Main St, City, State");
		patient.setEmergencyContact("Jane Doe - +0987654321");
		patient.setBloodType(BloodType.O_POSITIVE);
	}
	
	
	@Test
	void testAddPatient() {
		when(patientRepository.save(patient)).thenReturn(patient);
		
		Patient saved = patientService.addPatient(patient);
		
		assertNotNull(saved);
		assertEquals("John", saved.getFirstName());
		verify(patientRepository).save(patient);
	}
	
	@Test
	void testGetPatient() {
		when(patientRepository.findById(Long.valueOf(1))).thenReturn(Optional.of(patient));
		
		Patient found = patientService.getPatient(Long.valueOf(1));
		
		assertNotNull(found);
		assertEquals("John", found.getFirstName());
		verify(patientRepository).findById(Long.valueOf(1));
	}
	
	@Test
	void testUpdatePatient() {
		Patient toUpdate =new Patient();
		toUpdate.setFirstName("Johnny Updated");
		toUpdate.setLastName("Doe updated");
		
		when(patientRepository.findById(Long.valueOf(1))).thenReturn(Optional.of(patient));
		when(patientRepository.save(patient)).thenReturn(patient);
		
		Patient updated = patientService.updatePatient(toUpdate, Long.valueOf(1));
		
		assertNotNull(updated);
		assertEquals("Johnny Updated", updated.getFirstName());
		verify(patientRepository).save(patient);
	}
	
	@Test
	void testDeletePatient() {		
		when(patientRepository.findById(Long.valueOf(1))).thenReturn(Optional.of(patient));
		when(patientRepository.save(patient)).thenReturn(patient);
		
		Patient inactive = patientService.deletePatient(Long.valueOf(1));
		
		assertNotNull(inactive);
		assertEquals(false, inactive.getIsActive());
		verify(patientRepository).save(inactive);
	}
	
}