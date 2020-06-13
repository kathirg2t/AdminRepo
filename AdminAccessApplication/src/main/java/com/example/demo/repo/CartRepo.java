package com.example.demo.repo;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Cart;

@Repository
public interface CartRepo extends JpaRepository<Cart, Integer> {
	Page<Cart> findByProduct_Id(int id, Pageable paging);
	List<Cart> findByUserDetails_Id(int id);
	
//	@Query("Update Cart c Set c.quantity = 4 where c.id = 48")
	@Modifying(flushAutomatically = true, clearAutomatically = true)
	@Query("Update Cart c Set c.quantity =:myqty Where c.product.id =:pid  AND c.userDetails.id =:userId")
	int updateQty(@Param("myqty") Integer qty, @Param("pid") Integer productId, @Param("userId") Integer userId);
	
	@Query("Select c from Cart c where c.userDetails.id =:userId AND c.product.id=:pid")
	Cart findCart(@Param("userId") int userId,@Param("pid") int productId);
	
}

