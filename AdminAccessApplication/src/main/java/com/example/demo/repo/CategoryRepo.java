package com.example.demo.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.bean.CategoryObj;
import com.example.demo.entity.Category;

@Repository
public interface CategoryRepo extends JpaRepository<Category, Integer> {
	
	@Query("select new com.example.demo.bean.CategoryObj(c.id, c.name) from Category c")
	List<CategoryObj> findCategories();

}
