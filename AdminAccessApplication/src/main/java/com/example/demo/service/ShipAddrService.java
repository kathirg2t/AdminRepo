package com.example.demo.service;

import com.example.demo.entity.ShippingAddress;
import com.example.demo.exception.ShipAddrException;

public interface ShipAddrService {

	public String saveAddr(ShippingAddress shipAddr) throws ShipAddrException;
	
	public ShippingAddress getAddr(int userId) throws ShipAddrException;
	
}
