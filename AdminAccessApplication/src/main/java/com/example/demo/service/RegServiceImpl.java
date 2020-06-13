package com.example.demo.service;



import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Role;
import com.example.demo.entity.UserDetails;
import com.example.demo.exception.LoginException;
import com.example.demo.repo.RegisterRepo;
import com.example.demo.repo.RoleRepo;

@Service
public class RegServiceImpl implements RegService {
	
	@Autowired
	private RegisterRepo registerRepo; 
	
	@Autowired
	private RoleRepo roleRepo;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Override
	public String saveUser(UserDetails userDetails) throws Exception  {
		try {
			if(!checkUserName(userDetails.getUserName())) {
				Optional<Role> role = roleRepo.findByName("ADMIN");
				Set<Role> roles = new HashSet<>();
				if(role.isPresent()) {
					roles.add(role.get());
				}
				userDetails.setRoles(roles);
				String password = bCryptPasswordEncoder.encode(userDetails.getPassword());
				userDetails.setPassword(password);
				registerRepo.save(userDetails);
				return "success";
			} else {
				return "exist";
			}
			
		} catch (Exception ex)
		{
			throw new LoginException("User Failed to register");
		}
	}

	@Override
	public boolean checkUserName(String name) throws LoginException {
		try {
			Optional<UserDetails> userDetails=  registerRepo.findByUserName(name);
			if(userDetails.isPresent()) {
				return true;
			} else {
				return false;
			}
		} catch(Exception ex) {
			throw new LoginException("User not found");
		}
	}
	
	@Override
	public Role getRole() {
		return roleRepo.findByName("USER").get();
		
	}
	
	@Override
	public UserDetails getUser(String name) throws LoginException {
		try {
			Optional<UserDetails> userDetails = registerRepo.findByUserName(name);
			if(userDetails.isPresent()) {
				return userDetails.get();
			} else {
				throw new LoginException("User Not found");
			}
		} catch (Exception ex) {
			throw new LoginException("User Not found");
		}
	}

}
