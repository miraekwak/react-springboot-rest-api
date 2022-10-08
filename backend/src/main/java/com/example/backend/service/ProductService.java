package com.example.backend.service;

import com.example.backend.model.Category;
import com.example.backend.model.Product;

import java.util.List;
import java.util.UUID;

public interface ProductService {

    List<Product> getProductByCategory(Category category);

    List<Product> getAllProducts();

    Product createProduct(String productName, Category category, long price);

    Product createProduct(String productName, Category category, long price, String description);

    UUID deleteProduct(UUID productId);
}
