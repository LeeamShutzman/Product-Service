package com.example.demo.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.models.Product;
import com.example.demo.repositories.ProductRepository;
import com.example.demo.services.ProductService;

@RestController
@RequestMapping("products") //localhost:portNum/categories
public class ProductController {
	private ProductService productService;
	
	public ProductController(ProductService productService) {
		super();
		this.productService = productService;
	}

	public ProductService getProductService() {
		return productService;
	}

	public void setProductService(ProductService productService) {
		this.productService = productService;
	}
	
	@PostMapping("add")
	public Product addProduct(@RequestBody Product product) {
		return productService.addProduct(product);
	}
	
	@GetMapping("all")
	public List<Product> getAllProducts(){
		return productService.findAll();
	}

	
	@GetMapping()
	public List<Product> getProductsByCategory(@RequestParam long categoryID) {
		return productService.findByCategoryID(categoryID);
	}
	
	
	@GetMapping("product{id}")
	public Optional<Product> getProductByID(@PathVariable("id") long productID) {
		return productService.findByProductID(productID);
	}
	
	public void deleteById(long productID){
		productService.deleteById(productID);
	}
	
}
