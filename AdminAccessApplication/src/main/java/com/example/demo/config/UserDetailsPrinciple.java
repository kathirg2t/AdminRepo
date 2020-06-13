package com.example.demo.config;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.example.demo.entity.UserDetails;
import com.example.demo.exception.LoginException;
import com.example.demo.repo.RegisterRepo;

@Component
public class UserDetailsPrinciple implements UserDetailsService {
	
	@Autowired
	RegisterRepo registerRepo;
		
	@Override
	public org.springframework.security.core.userdetails.UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserDetails userDetails = registerRepo.findByUserName(username).get();
		if(userDetails == null) {
			throw new LoginException("User not found");
		}
		return new UserPrinciple(userDetails);
		
		
	}

}
