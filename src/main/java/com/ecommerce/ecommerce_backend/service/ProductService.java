package com.ecommerce.ecommerce_backend.service;

import com.ecommerce.ecommerce_backend.model.Product;
import com.ecommerce.ecommerce_backend.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service // Tells Spring Boot this class contains our business logic


public class ProductService {
	// MOVED FROM CONTROLLER: Only the Service talks to the Repository now!
    @Autowired
    private ProductRepository productRepository;

    // 1. Business logic for fetching & seeding products
    public List<Product> getAllProducts() {
        if (productRepository.count() == 0) {
            productRepository.save(new Product("Laptop", 1200.00));
            productRepository.save(new Product("Headphones", 150.00));
            productRepository.save(new Product("Running Shoes", 80.00));
        }
        return productRepository.findAll();
    }

    // 2. Business logic for saving a new product
    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    // 3. Business logic for finding and updating an existing product
    public Product updateProduct(Long id, Product updatedProduct) {
        return productRepository.findById(id).map(product -> {
            product.setName(updatedProduct.getName());
            product.setPrice(updatedProduct.getPrice());
            return productRepository.save(product);
        }).orElseThrow(() -> new RuntimeException("Product not found with id " + id));
    }

    // 4. Business logic for deleting a product
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }
}
