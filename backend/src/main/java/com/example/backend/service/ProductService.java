package com.example.backend.service;

import com.example.backend.model.Category;
import com.example.backend.model.Product;

import java.util.List;

public interface ProductService {

    List<Product> getProductByCategory(Category category);

    List<Product> getAllProducts();

    Product createProduct(String productName, Category category, long price);

    Product createProduct(String productName, Category category, long price, String description);
}
