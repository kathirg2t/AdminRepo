package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Cart;
import com.example.demo.entity.Product;
import com.example.demo.exception.CartException;
import com.example.demo.repo.CartRepo;
import com.example.demo.repo.ProductRepo;

@Service
@Transactional
public class CartServiceImpl implements CartService {
	
	@Autowired
	private CartRepo cartRepo; 
	
	@Autowired
	private ProductRepo productRepo;
	
	@PersistenceContext\
	EntityManager em;
		
	@Override
	public void saveCart(Cart cart) throws CartException {
		int quantity;
		try {
			Optional<List<Cart>> cartCheck = checkExist(cart.getProduct().getId());
			System.out.println(cartCheck.get().size());
			System.out.println(cartCheck);
			
			if(cartCheck.get().size() == 0) {
				 quantity = cart.getQuantity();
				 cartRepo.save(cart);
			} else {
				Cart ct = cartCheck.get().get(0);
				quantity = cart.getQuantity();	
				int totalQty = quantity + cart.getQuantity();
				cart.setQuantity(totalQty);
				cart.setId(ct.getId());
				cartRepo.save(cart);
			}
			Optional<Product> optionalProduct = productRepo.findById(cart.getProduct().getId());
			if(optionalProduct.isPresent()) {
				Product product = optionalProduct.get();
				int qty = product.getQuantity() - quantity;
				product.setQuantity(qty);
				productRepo.save(product);
			}
			
			
		} catch(Exception ex) {
			throw new CartException("Cart Failed to save");
		}
	}
	
	@Override
	public Optional<List<Cart>> checkExist(int id) throws CartException {
		try {
		    Pageable paging = PageRequest.of(0, 1);
		    System.out.println(cartRepo.findByProduct_Id(id, paging).getContent());
		    Optional<List<Cart>> cart = Optional.ofNullable(cartRepo.findByProduct_Id(id, paging).getContent());
		    return cart;
		} catch(Exception ex) {
			throw new CartException("Cart not found");
		}
	}
	
	@Override
	public List<Cart> getOrderDetails(int id) throws CartException {
		try {
			return cartRepo.findByUserDetails_Id(id);
		} catch(Exception ex) {
			throw new CartException("Cart not found");
		}
	}
	
	@Override
	public List<Cart> getCarts() throws CartException {
		try {
			return cartRepo.findAll();
		} catch(Exception ex) {
			throw new CartException("Cart not found");
		}
	}
	
	@Override
	public String updateQty(int productId, int userId, int qty)  {
		try {
			Cart cart = cartRepo.findCart(userId, productId);
			int updateCount = cartRepo.updateQty(qty, productId, userId);
			int subQty = qty - cart.getQuantity();
//			productRepo.updatePrdtQty(subQty, productId);
			if(updateCount > 0) {
				return "success";
			} else {
				return "no record found";
			}
		} catch(Exception ex) {
		    return "fails";
		}
		
	}
		

}
