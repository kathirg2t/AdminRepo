package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.ShippingAddress;
import com.example.demo.exception.ShipAddrException;
import com.example.demo.repo.ShipAddrRepo;

@Service
public class ShipAddrServiceImpl implements ShipAddrService {

	@Autowired
	private ShipAddrRepo shipAddrRepo;
	
	@Override
	public String saveAddr(ShippingAddress shipAddr) throws ShipAddrException {
		try {
			shipAddrRepo.save(shipAddr);
		} catch(Exception ex) {
			throw new ShipAddrException("Ship Failed to save");
		}
		return null;
	}

}
