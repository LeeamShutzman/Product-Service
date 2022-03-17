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
	
	
	@GetMapping("getProductsByCategoryID")
	public List<Product> getProductsByCategory(@RequestParam long categoryID) {
		return productService.findProductsByCategoryID(categoryID);
	}
	
	
	@GetMapping("getProductByID")
	public Optional<Product> getProductByID(@RequestParam long productID) {
		return productService.findByProductID(productID);
	}
	
	@GetMapping("getProductsBySupplierID")
	public List<Product> getProductsBySupplier(@RequestParam long supplierID) {
		return productService.findProductsBySupplierID(supplierID);
	}
	
	
	@GetMapping("getBySupplierIDAndCategoryID")
	public List<Product> getProductsBySupplierIDAndCategoryID(@RequestParam long supplierID, @RequestParam long categoryID ){
		return productService.findProductsBySupplierIDAndCategoryID(supplierID, categoryID);
	}
	
	
	public void deleteById(long productID){
		productService.deleteById(productID);
	}
	
}
	
	
	
	
	
	

