package com.example.demo.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.entity.Cart;
import com.example.demo.entity.Product;
import com.example.demo.entity.UserDetails;
import com.example.demo.exception.CartException;
import com.example.demo.exception.LoginException;
import com.example.demo.exception.ProductNotFoundException;
import com.example.demo.exception.ShipAddrException;
import com.example.demo.service.CartService;

@ControllerAdvice
public class ExceptionController {

	
	@ExceptionHandler(value = LoginException.class)
	public String handleException(final LoginException ex, final Model model) {
		 model.addAttribute("error", ex.getLocalizedMessage());
		 model.addAttribute("regUser", new UserDetails());
		 return "register";
	}
	
	@ExceptionHandler(value = ProductNotFoundException.class)
	public String productExceptionHandler(final ProductNotFoundException ex, final Model model) {
		 model.addAttribute("error", ex.getLocalizedMessage());
		 model.addAttribute("product", new Product());
		 return "register";
	}
	
	@ExceptionHandler(value = CartException.class)
	public String cartExceptionHandler(final CartException ex, final Model model, RedirectAttributes redir) {
		redir.addFlashAttribute("error", ex.getLocalizedMessage());
		 return "redirect:/buyProducts";
	}
	
	@ExceptionHandler(value = ShipAddrException.class)
	public String shipExceptionHandler(final ShipAddrException ex, final Model model, RedirectAttributes redir) {
		redir.addFlashAttribute("error", ex.getLocalizedMessage());
		 return "redirect:/shpping";
	}
}
