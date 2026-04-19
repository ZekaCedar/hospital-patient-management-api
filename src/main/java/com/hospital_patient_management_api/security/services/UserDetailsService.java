package com.hospital_patient_management_api.security.services;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/*
* need UserDetailsService for getting UserDetails object.
* You can look at UserDetailsService interface that has only one method:
* */
public interface UserDetailsService {

    UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;

}
