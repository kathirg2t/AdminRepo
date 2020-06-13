package com.example.demo.controller;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.entity.ShippingAddress;
import com.example.demo.exception.ShipAddrException;

@Controller
public class ShippinController {
	
	@GetMapping("/shpping")
	public String shipPage(Model model) {
		model.addAttribute("shipAddr", new ShippingAddress());
		return "userAccount";
	}
	 
	@PostMapping("/saveAddr")
	public String saveAddr(@Valid @ModelAttribute("shipAddr") ShippingAddress shipAddr, BindingResult bindingResult, Model model ) {
		
	}

}
