package com.example.backend.controller;

public record CreateProductRequest(
   String productName, String category, long price
) {
}
