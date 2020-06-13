package com.example.demo.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "shipping_address")
public class ShippingAddress {
	
	@Id
	@GeneratedValue
	private int id;
	
	@NotEmpty(message = "Please add address")
	private String address;
	
	@NotEmpty(message = "Please add city")
	private String city;
	
	@NotEmpty(message = "Please add state")
	private String state;
	
	@Min(value = 4, message = "Please enter atleast 4 digit")
	private int pinCode;
	
	@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	@JoinColumn(name = "user_id")
	private UserDetails userDetails;
	
	
	public ShippingAddress() {
		
	}

	
	public ShippingAddress(String address, String city, String state, int pinCode, UserDetails userDetails) {
		super();
		this.address = address;
		this.city = city;
		this.state = state;
		this.pinCode = pinCode;
		this.userDetails = userDetails;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public int getPinCode() {
		return pinCode;
	}

	public void setPinCode(int pinCode) {
		this.pinCode = pinCode;
	}

	public UserDetails getUserDetails() {
		return userDetails;
	}

	public void setUserDetails(UserDetails userDetails) {
		this.userDetails = userDetails;
	}


	@Override
	public String toString() {
		return "ShippingAddress [id=" + id + ", address=" + address + ", city=" + city + ", state=" + state
				+ ", pinCode=" + pinCode + ", userDetails=" + userDetails + "]";
	}

}
