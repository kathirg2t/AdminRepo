package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import com.example.demo.entity.Cart;
import com.example.demo.exception.CartException;


public interface CartService {
	
	public void saveCart(Cart cart) throws CartException; 
	public Optional<List<Cart>> checkExist(int id) throws CartException;
	public List<Cart> getOrderDetails(int id) throws CartException;
	public List<Cart> getCarts() throws CartException;
	public String updateQty(int productId, int userId, int qty) throws CartException;
	
	
}
