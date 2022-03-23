package com.example.demo.services;

import com.example.demo.models.Product;
import com.example.demo.repositories.ProductRepository;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@WebMvcTest(ProductService.class)
class ProductServiceTest {

    @Autowired
    ProductService productService;

    @MockBean
    ProductRepository productRepository;

    public Product product1 = new Product(1,"testName1",2,2,25);
    public Product product2 = new Product(2,"testName2",3,2,30);
    public Product product3 = new Product(3,"testName3",2,3,35);
    public Product product4 = new Product(4,"testName4",3,3,40);

    public List<Product> testList = new ArrayList<>(Arrays.asList(product1,product2,product3,product4));


    @Test
    void addProduct() {
        when(productRepository.save(product1)).thenReturn(product1);
        assertEquals(productService.addProduct(product1).toString(), product1.toString());
    }

    @Test
    void findAll() {
        when(productRepository.findAll()).thenReturn(testList);
        assertEquals(productService.findAll(),testList);
    }

    @Test
    void findByProductID() {
        Optional<Product> productOptional = Optional.of(product1);
        when(productRepository.findById(1L)).thenReturn(productOptional);
        assertEquals(productService.findByProductID(1L),productOptional);
    }

    @Test
    void findProductsByCategoryID() {
        List<Product> cat2List = new ArrayList<>(Arrays.asList(testList.get(0),testList.get(1)));
        when(productRepository.findByCategoryID(2L)).thenReturn(cat2List);
        assertEquals(productService.findProductsByCategoryID(2L),cat2List);
    }

    @Test
    void findProductsBySupplierID() {
        List<Product> supp2List = new ArrayList<>(Arrays.asList(testList.get(0),testList.get(2)));
        when(productRepository.findBySupplierID(2L)).thenReturn(supp2List);
        assertEquals(productService.findProductsBySupplierID(2L),supp2List);
    }

    @Test
    void findProductsBySupplierIDAndCategoryID() {
        List<Product> supp2cat2List = new ArrayList<>(Arrays.asList(testList.get(0)));
        when(productRepository.findBySupplierIDAndCategoryID(2L, 2L)).thenReturn(supp2cat2List);
        assertEquals(productService.findProductsBySupplierIDAndCategoryID(2L,2L),supp2cat2List);
    }

    @Test
    void deleteProduct() {
        Optional<Product> productOptional = Optional.of(product1);
        productService.deleteProduct(1L);
        verify(productRepository, times(1)).deleteById(1L);
    }

    @Test
    void updateProduct() {
        Optional<Product> productOptional = Optional.of(product1);
        when(productRepository.findById(1L)).thenReturn(productOptional);
        when(productRepository.save(product1)).thenReturn(product1);
        assertEquals(productService.updateProduct(1L, product1),product1);
    }
}