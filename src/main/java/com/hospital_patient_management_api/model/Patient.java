package com.hospital_patient_management_api.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Entity
@Table(name="patient")
@Data
public class Patient{
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank(message="first Name is required")
	private String firstName;
	
	@NotBlank(message="Last Name is required")
	private String lastName;
	
	@NotNull(message="Date Of Birth is required")
	private LocalDate dateOfBirth;
	
	@NotBlank(message="phone number is required")
	private String phoneNumber;
	
	@Email(message = "Email should be valid")
	@Column(unique=true)
	private String email;
	
//	@NotBlank(message="Emergency Contact is required")
	private String address;
	
//	@NotBlank(message="Emergency Contact is required")
	private String emergencyContact;
	
//	@NotNull
	private BloodType bloodType;
	
//	@NotNull
	@JsonProperty("isActive")
	private Boolean isActive = true;
	
	@CreationTimestamp
	private LocalDateTime createdAt;
	
	@UpdateTimestamp
	private LocalDateTime updatedAt;
	
	
}