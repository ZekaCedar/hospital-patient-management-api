package com.hospital_patient_management_api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
//public class HospitalPatientManagementApiApplication {
public class HospitalPatientManagementApiApplication extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(HospitalPatientManagementApiApplication.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(HospitalPatientManagementApiApplication.class, args);
	}

}
