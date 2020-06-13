package com.example.demo.service;

import java.util.List;

import com.example.demo.bean.CategoryObj;
import com.example.demo.entity.Category;
import com.example.demo.exception.CategoryNotFoundException;
import com.example.demo.exception.ProductNotFoundException;

public interface CategoryService {
	public void saveCategory(Category category) throws CategoryNotFoundException;
	public List<CategoryObj> getCategories() throws CategoryNotFoundException;
	public Category getCategory(int id) throws CategoryNotFoundException;
	public void deleteCategory(int id) throws CategoryNotFoundException;
	
}