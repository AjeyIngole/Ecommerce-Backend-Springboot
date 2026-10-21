package com.ecommerce.ecommerce_backend.controller;

import com.ecommerce.ecommerce_backend.model.Product;
import com.ecommerce.ecommerce_backend.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*") // <--- Add this annotation here!
@RequestMapping("/api/products")
public class ProductController {

    // Now the Controller ONLY talks to the Service layer!
    @Autowired
    private ProductService productService;

    // 1. GET: Fetch all products
    @GetMapping
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    // 2. POST: Add a new product
    @PostMapping
    public Product createProduct(@RequestBody Product product) {
        return productService.createProduct(product);
    }

    // 3. PUT: Update an existing product
    @PutMapping("/{id}")
    public Product updateProduct(@PathVariable Long id, @RequestBody Product updatedProduct) {
        return productService.updateProduct(id, updatedProduct);
    }

    // 4. DELETE: Remove a product
    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
    }
}