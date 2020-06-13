package com.example.demo.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "auth_role")
public class Role {
	@Id
	@GeneratedValue
	private int id;

	private String name;
	
//	@ManyToMany(mappedBy = "roles",  cascade = CascadeType.ALL)
//	private Set<UserDetails> userDetails;
	
	public Role() {
		
	}
	
	public Role(String name) {
		super();
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setId(int id) {
		this.id = id;
	}

//	public Set<UserDetails> getUserDetails() {
//		return userDetails;
//	}
//
//	public void setUserDetails(Set<UserDetails> userDetails) {
//		this.userDetails = userDetails;
//	}

	public int getId() {
		return id;
	}

	
	
	
}
