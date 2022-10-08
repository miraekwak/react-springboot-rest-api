package com.example.backend.repository;

import com.example.backend.model.Category;
import com.example.backend.model.Product;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ProductRepository {

    List<Product> findAll();

    Product insert(Product product);

    Product update(Product product);

    Optional<Product> findById(UUID productId);

    Optional<Product> findByName(String productName);

    List<Product> findByCategory(Category category);

    UUID deleteProduct(UUID productId);

    void deleteAll();
}
