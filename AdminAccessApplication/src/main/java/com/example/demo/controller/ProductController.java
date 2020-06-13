package com.example.demo.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.bean.CategoryObj;
import com.example.demo.entity.Cart;
import com.example.demo.entity.Product;
import com.example.demo.entity.UserDetails;
import com.example.demo.exception.CartException;
import com.example.demo.exception.CategoryNotFoundException;
import com.example.demo.exception.ProductNotFoundException;
import com.example.demo.service.CartService;
import com.example.demo.service.CategoryService;
import com.example.demo.service.ProductServiceImpl;

@Controller
public class ProductController {
	
	@Autowired
	ProductServiceImpl productService;
	
	@Autowired
	CategoryService categoryService;
	
	@Autowired
	CartService cartService;
	
	private String productImageFolderPath = System.getProperty("user.dir")+"/src/main/resources/static/productImages";
	
	
	@ModelAttribute("cartSize")
	public Integer getCartCount( HttpSession session, Model model ) throws CartException {
		UserDetails ud = (UserDetails) session.getAttribute("user");
		List<Cart> listCart= cartService.getOrderDetails(ud.getId());
		System.out.println(listCart.size());
		return listCart.size();
	}
	
	
	@GetMapping("/addProduct")
	public String product(Model model) throws CategoryNotFoundException {
		model.addAttribute("categories", categoryService.getCategories());
		model.addAttribute("product", new Product());
		return "addProduct";
	}
	
	@GetMapping("/getCategories")
	@ResponseBody
	public List<CategoryObj> getCategories() throws CategoryNotFoundException {
		return categoryService.getCategories();
	}
	
	@GetMapping("/productList")
	public String getProducts(Model model) throws CategoryNotFoundException {
		model.addAttribute("products", productService.getProducts());
		return "productList";
	}
	
	@PostMapping("/addProduct")
	public String addProduct(@Valid @ModelAttribute Product product, BindingResult bindingResult, 
			@RequestParam("imageFile")  MultipartFile imageFile, Model model, RedirectAttributes redirAttr ) throws ProductNotFoundException, IOException, CategoryNotFoundException {
		model.addAttribute("categories", categoryService.getCategories());
		model.addAttribute("product", product);

		if((imageFile.isEmpty() && product.getImage().isEmpty()) || product.getCategory() == null || bindingResult.hasErrors()) {
			if(imageFile.isEmpty() && product.getImage().isEmpty() ) {
				model.addAttribute("imageError", "Image Field is required");
			}
			if( product.getCategory() == null) {
				model.addAttribute("categoryError", "Category Field is required");
			}
			return "addProduct";
		}		
		
		if(!imageFile.isEmpty()) {
			try {
				String folder = productImageFolderPath;
				Path path = Paths.get(folder, imageFile.getOriginalFilename());
				Files.write(path, imageFile.getBytes());
				product.setImage(imageFile.getOriginalFilename());
			} catch(IOException ex) {
				throw new ProductNotFoundException("Image failed to save");
			}
		}
		
		productService.saveProduct(product);
		redirAttr.addFlashAttribute("success", "Successfully saved");
		return "redirect:/productList";
	}
	
	@GetMapping("/editProduct")
	public String editProduct(@RequestParam("productId") int productId, Model model) throws CategoryNotFoundException {
		model.addAttribute("categories", categoryService.getCategories());
		model.addAttribute("product", productService.getProductById(productId));
		return "addProduct";
	}

	@GetMapping("/deleteProduct")
	public String deleteProduct(@RequestParam("productId") int productId, RedirectAttributes redir) {
		productService.deleteProductById(productId);
		redir.addFlashAttribute("success", "Deleted Successfully");
		return "redirect:/productList";
	}
	
	@GetMapping("/buyProducts")
	public String getAllProducts(Model model) throws CategoryNotFoundException  {
		model.addAttribute("products", productService.getProducts());
		model.addAttribute("categories", categoryService.getCategories());
		model.addAttribute("cart", new Cart());
		 return "buyProducts";
	}
	
	
}
