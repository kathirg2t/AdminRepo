package com.example.demo.service;

import java.util.List;

import com.example.demo.bean.ProductObj;
import com.example.demo.entity.Product;
import com.example.demo.exception.ProductNotFoundException;


public interface ProductService {
	public void saveProduct(Product product)  throws Exception;
	
	public List<Product> getProducts() throws ProductNotFoundException;
	
	public Product getProductById(int id) throws ProductNotFoundException;
	
	public void deleteProductById(int id) throws ProductNotFoundException;
	
	
}
