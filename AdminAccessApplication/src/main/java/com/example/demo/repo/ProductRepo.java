package com.example.demo.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.bean.ProductObj;
import com.example.demo.entity.Product;

@Repository
public interface ProductRepo extends JpaRepository<Product, Integer> {
	@Query("Select new com.example.demo.bean.ProductObj(p.id, p.name) from Product p")
	List<ProductObj> findProducts();
	
	@Modifying(clearAutomatically = true, flushAutomatically = true)
	@Query("Update Product p Set p.quantity = p.quantity - ?1 Where p.id=?2 ")
	int updatePrdtQty(int qty, int productId);
}
