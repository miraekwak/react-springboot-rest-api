package com.example.backend.model;

import java.util.UUID;

public record OrderItem(
        UUID productId, Category category, long price, int quantity
) {
}
