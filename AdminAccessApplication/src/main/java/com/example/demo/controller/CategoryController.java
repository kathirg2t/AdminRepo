package com.example.demo.controller;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.entity.Cart;
import com.example.demo.entity.Category;
import com.example.demo.entity.UserDetails;
import com.example.demo.exception.CartException;
import com.example.demo.exception.CategoryNotFoundException;
import com.example.demo.service.CartService;
import com.example.demo.service.CategoryService;

@Controller
public class CategoryController {

	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	CartService cartService;
	
	@ModelAttribute("cartSize")
	public Integer getCartCount( HttpSession session, Model model ) throws CartException {
		UserDetails ud = (UserDetails) session.getAttribute("user");
		List<Cart> listCart= cartService.getOrderDetails(ud.getId());
		System.out.println(listCart.size());
		return listCart.size();
	}
	
	@GetMapping("addCategory")
	public String addCategory(Model model) {
		model.addAttribute("category", new Category());
		return "addCategory";
	}
	
	@GetMapping("/categoryList")
	public String getCategories(Model model) throws CategoryNotFoundException  {
		 model.addAttribute("categories", categoryService.getCategories());
		 return "categoryList";
	}
	
	@PostMapping("/addCategory")
	public String addCategory(@Valid @ModelAttribute("category") Category category, BindingResult bindingResult,Model model, RedirectAttributes redir) throws CategoryNotFoundException {
		
		if(bindingResult.hasErrors()) {
			model.addAttribute("category", category);
			return "addCategory";
		}
		categoryService.saveCategory(category);
		redir.addFlashAttribute("success", "Category created successfully");
		return "redirect:/categoryList";
	}
	
	@GetMapping("/editCategory")
	public String editCategory(@RequestParam("catId") int id , RedirectAttributes redir, Model model) throws CategoryNotFoundException {
		model.addAttribute("category", categoryService.getCategory(id));
		return "addCategory";
	}
	
	@GetMapping("/deleteCategory")
	public String deleteCategory(@RequestParam("catId") int catId, RedirectAttributes redir) throws CategoryNotFoundException {
		categoryService.deleteCategory(catId);
		redir.addFlashAttribute("success", "Deleted Successfully");
		return "redirect:/categoryList";
	}

	
}
