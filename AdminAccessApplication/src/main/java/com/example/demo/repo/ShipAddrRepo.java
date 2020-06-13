package com.example.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.ShippingAddress;

public interface ShipAddrRepo extends JpaRepository<ShippingAddress, Integer> {

	ShippingAddress findByUserDetails_Id(int userId);
	
}
