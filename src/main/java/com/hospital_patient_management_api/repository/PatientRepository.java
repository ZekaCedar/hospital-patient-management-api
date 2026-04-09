package com.hospital_patient_management_api.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hospital_patient_management_api.model.Patient;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long>{
	
	Optional<Patient> findById(Long id);

}
