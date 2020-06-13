package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.entity.Cart;
import com.example.demo.entity.Product;
import com.example.demo.entity.UserDetails;
import com.example.demo.exception.CartException;
import com.example.demo.service.CartService;
import com.example.demo.service.ProductService;

@Controller
public class CartController {
	
	@Autowired
	private CartService cartService;
	
	@Autowired
	private ProductService productService;
	
	@ModelAttribute("cartSize")
	public Integer getCartCount( HttpSession session, Model model ) throws CartException {
		UserDetails ud = (UserDetails) session.getAttribute("user");
		List<Cart> listCart= cartService.getOrderDetails(ud.getId());
		System.out.println(listCart.size());
		return listCart.size();
	}
	
	@PostMapping("/saveCart")
	public String saveCart(@Valid @ModelAttribute Cart cart, BindingResult bindingResult,  RedirectAttributes redir) throws CartException {
		if(bindingResult.hasErrors()) {
			return "redirect:/buyProducts";
		}
		cartService.saveCart(cart);
		redir.addFlashAttribute("success", "Successfully added to cart");
		return  "redirect:/buyProducts";
	}
	
	@GetMapping("/cartDetails") 
	public String getOrderDetails(Model model, HttpSession session) throws CartException {
		UserDetails user = (UserDetails) session.getAttribute("user");
		List<Cart> orders = cartService.getOrderDetails(user.getId());
		List<Cart> carts = orders.stream().map(cart -> {
//			Product product = productService.getProductById(cart.getProduct().getId());
			double price = cart.getProduct().getPrice();
			int qty = cart.getQuantity();
			double totalPrice = price * qty;
			cart.getProduct().setPrice(totalPrice);
			return cart; }).collect(Collectors.toList());
		System.out.println(carts);
		List<Product> p = new ArrayList<>();
		p.add(new Product("test", "test.png", 10));
		
		Integer d = carts.stream().map(m -> m.getProduct().getPrice()).collect(Collectors.summingInt(m -> m.intValue()));
		System.out.println(d);
		model.addAttribute("totalPrice", d);
		model.addAttribute("orders", carts);
		return "orders";
	}
	
	@PostMapping(path = "/updateQty")
//	@ResponseBody
	public String updateQty(@RequestParam(name ="productID", required = true) Integer productId, @RequestParam(name ="productQty", required = true) Integer productQty, 
			@RequestParam(name ="userId", required = true) Integer userId, RedirectAttributes redir) throws CartException {
		try {
			String str = cartService.updateQty(productId, userId, productQty);
			if(str == "success") {
				redir.addFlashAttribute("success", "updated successfully");
			} else {
				redir.addFlashAttribute("error", "update fails");
			}
		} catch(Exception ex) {
			throw new CartException(ex.getLocalizedMessage());
		}
		return "redirect:/cartDetails";
	}
	
}
