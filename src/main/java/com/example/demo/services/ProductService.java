package com.example.demo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.models.Product;
import com.example.demo.repositories.ProductRepository;

@Service
public class ProductService {
	@Autowired
	private ProductRepository productRepository;

	public ProductService(ProductRepository productRepository) {
		super();
		this.productRepository = productRepository;
	}

	public ProductRepository getProductRepository() {
		return productRepository;
	}

	public void setProductRepository(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}
	
	
	public List<Product> findProductsByCategoryID(long categoryID){
		return productRepository.findByCategoryID(categoryID);
	}
	
	
	public List<Product> findAll(){
		return productRepository.findAll();
	}

	public Product addProduct(Product product) {
		return productRepository.save(product);
	}
	
	
	public Optional<Product> findByProductID(long productID) {
		return productRepository.findById(productID);
	}

	

	public void deleteById(long productID) {
		productRepository.deleteById(productID);
	}
	
	
	public List<Product> findProductsBySupplierID(long supplierID){
		return productRepository.findBySupplierID(supplierID);
	}
	
	
	public List<Product> findProductsBySupplierIDAndCategoryID(long supplierID, long categoryID){
		return productRepository.findBySupplierIDAndCategoryID(supplierID, categoryID);
	}
	


}
