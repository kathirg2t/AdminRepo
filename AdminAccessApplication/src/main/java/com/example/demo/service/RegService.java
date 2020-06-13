package com.example.demo.service;

import org.springframework.security.authentication.LockedException;

import com.example.demo.entity.Role;
import com.example.demo.entity.UserDetails;
import com.example.demo.exception.LoginException;

public interface RegService {

	String saveUser(UserDetails userDetails) throws Exception;
	boolean checkUserName(String name) throws Exception;
	public Role getRole();
	public UserDetails getUser(String name) throws LoginException;
	
}
