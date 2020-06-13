package com.example.demo.config;

import java.util.Collection;
import java.util.Collections;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class UserPrinciple implements UserDetails {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	com.example.demo.entity.UserDetails ud;
	
	public UserPrinciple(com.example.demo.entity.UserDetails userDetails) {
		this.ud = userDetails;
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return Collections.singletonList(new SimpleGrantedAuthority("ADMIN"));
//		List<SimpleGrantedAuthority> list = new ArrayList<SimpleGrantedAuthority>();
//		for(Role role : ud.getRoles()) {
//			list.add(new SimpleGrantedAuthority(role.getName()));
//		}
//		return list;
	}

	@Override
	public String getPassword() {
		return ud.getPassword();
	}

	@Override
	public String getUsername() {
		return ud.getUserName();
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return false;
	}
		
	
	
}
