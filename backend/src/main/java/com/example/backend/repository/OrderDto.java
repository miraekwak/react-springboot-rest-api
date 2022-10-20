package com.example.backend.repository;

import com.example.backend.model.Email;
import com.example.backend.model.OrderStatus;

import java.time.LocalDateTime;
import java.util.UUID;

public record OrderDto(
        UUID orderId, UUID memberId, Email email, String address, String postcode,
        OrderStatus orderStatus,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
