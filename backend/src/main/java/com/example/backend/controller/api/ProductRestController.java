package com.example.backend.controller.api;

import com.example.backend.controller.CreateProductRequest;
import com.example.backend.model.Category;
import com.example.backend.model.Product;
import com.example.backend.service.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class ProductRestController {

    private final ProductService productService;

    public ProductRestController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/api/v1/products")
    public List<Product> productList(@RequestParam Optional<Category> category) {
        return category
                .map(productService::getProductByCategory)
                .orElse(productService.getAllProducts());
    }

    @PostMapping("/api/v1/products")
    public Product createProduct(@RequestBody CreateProductRequest productRequest){
        return productService.createProduct(productRequest.productName(), Category.valueOf(productRequest.category()), productRequest.price());
    }

    @GetMapping("/api/v1/products/category")
    public List<String> categoryList(){
        return Arrays.stream(Category.values()).map((category -> category.toString())).collect(Collectors.toList());
    }

    @DeleteMapping("/api/v1/products/{productId}")
    public UUID deleteProduct(@PathVariable UUID productId) {
        return productService.deleteProduct(productId);
    }
}
