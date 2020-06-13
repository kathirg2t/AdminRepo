package com.example.demo.service;

import java.security.cert.PKIXRevocationChecker.Option;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.bean.CategoryObj;
import com.example.demo.entity.Category;
import com.example.demo.entity.Product;
import com.example.demo.exception.CategoryNotFoundException;
import com.example.demo.exception.ProductNotFoundException;
import com.example.demo.repo.CategoryRepo;
import com.example.demo.repo.ProductRepo;



@Service
public class CategoryServiceImpl implements CategoryService {
	
	@Autowired
	CategoryRepo categoryRepo;
	
	@Override
	public void saveCategory(Category category) throws CategoryNotFoundException {
		try {
			categoryRepo.save(category);
		} catch(RuntimeException ex) {
			throw new CategoryNotFoundException("User failed to save");
		}
	}
	@Override
	public List<CategoryObj> getCategories() throws CategoryNotFoundException {
		try {
			return categoryRepo.findCategories();
		} catch(Exception ex) {
			throw new CategoryNotFoundException("Category not found");
		}
	}
	
	@Override
	public Category getCategory(int id) throws CategoryNotFoundException {
		try {
			Optional<Category> category = categoryRepo.findById(id);
			if(category.isPresent()) {
				return category.get();
			} else {
				throw new CategoryNotFoundException("Category not found");
			}
		} catch(Exception ex) {
			throw new CategoryNotFoundException("Category not found");
		}
	} 
	
	@Override
	public void deleteCategory(int id) throws CategoryNotFoundException {
		categoryRepo.deleteById(id);
	}
	
}
