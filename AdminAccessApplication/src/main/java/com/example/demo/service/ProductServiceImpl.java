package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.bean.ProductObj;
import com.example.demo.entity.Category;
import com.example.demo.entity.Product;
import com.example.demo.exception.ProductNotFoundException;
import com.example.demo.repo.CategoryRepo;
import com.example.demo.repo.ProductRepo;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {
	
	@Autowired
	ProductRepo productRepo;
	
	@Autowired
	CategoryRepo categoryRepo;
	
	@Override
	public void saveProduct(Product product) throws ProductNotFoundException {
		try {
			int id =  product.getCategory().getId();
			Optional<Category> category =  categoryRepo.findById(id);
			if(category.isPresent()) {
				productRepo.save(product);
				product.setCategory(category.get());
			} else {
				throw new ProductNotFoundException("Product failed to save");
			}
		} catch(ProductNotFoundException ex) {
			throw new ProductNotFoundException("User failed to save");
		}
	}

	@Override
	public List<Product> getProducts() throws ProductNotFoundException {
		try {
			return	productRepo.findAll();
		} catch(Exception ex) {
			throw new ProductNotFoundException("Product not found");
		}
	}
	
	
	@Override
	public Product getProductById(int id) throws ProductNotFoundException {
		try {
			return productRepo.findById(id).get();
		} catch(Exception ex) {
			throw new ProductNotFoundException("Product not found");
		}
	}
	
	@Override
	public void deleteProductById(int id) throws ProductNotFoundException {
		try {
			productRepo.deleteById(id);
		} catch(Exception ex) {
			throw new ProductNotFoundException("Product not found");
			
		}
	}
	
	
}
