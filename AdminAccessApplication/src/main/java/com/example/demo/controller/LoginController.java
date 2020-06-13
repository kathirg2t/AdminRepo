package com.example.demo.controller;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.entity.Cart;
import com.example.demo.entity.Role;
import com.example.demo.entity.UserDetails;
import com.example.demo.exception.CartException;
import com.example.demo.service.CartService;
import com.example.demo.service.RegService;

@Controller
@SessionAttributes("user")
public class LoginController {
	
	@Autowired
	public RegService regService;
	
	@Autowired
	public CartService cartService;

	
	@GetMapping("/")
	public String home(Principal principal, Model model, HttpSession session ) throws CartException {
		UserDetails userDetails = regService.getUser(principal.getName());
		model.addAttribute("user", userDetails);
		List<Cart> listCart= cartService.getOrderDetails(userDetails.getId());
		model.addAttribute("cartSize", listCart.size());
		return "home";
		
	}
	
	@GetMapping("/access_denied")
	public String login(Model model) {
		model.addAttribute("error", "Access denied");
		return "login";
	}

	@GetMapping("/login")
	public String login() {
		return "login";
	}
	
	@GetMapping("/register")
	public String register(ModelMap model) {
		model.addAttribute("regUser", new UserDetails());
		return "register";
	}
		
	@PostMapping("/save_user")
	public String save(ModelMap model, @Valid @ModelAttribute UserDetails userDetails, BindingResult bindingResult, RedirectAttributes redirect ) throws Exception {
		
		if(bindingResult.hasErrors()) {	
			return "register";
		}
		
		String msg = regService.saveUser(userDetails);
		if(msg == "success") {
			redirect.addFlashAttribute("regUser", new UserDetails());
			redirect.addFlashAttribute("success", "User Created Successfully");
		} else {
			redirect.addFlashAttribute("regUser", userDetails);
			redirect.addFlashAttribute("msg", "User already exist");
		}
		return "redirect:/register";
	}
	
	@ResponseBody
	@GetMapping("/role")
	public Role getRole() {
		return regService.getRole();
	}
	
	@GetMapping(path="/res")
	@ResponseBody
	public String res() {
		String str = "<table class='table table-bordered'><thead><tr>"
				+ "<th>test</th>"
				+ "<th>test1</th>"
				+ "</tr>"
				+ "</thead>"
				+ "<tbody>"
				+ "<tr>"
				+ "<td>data</td>"
				+ "<td>data</td>"
				+ "</tr>"
				+ "/<tbody>"
				+ "</table>";
		return str;
	}
	
}
