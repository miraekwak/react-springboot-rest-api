package com.example.backend.controller;

import com.example.backend.model.OrderItem;

import java.util.List;
import java.util.UUID;

public record CreateOrderRequest(
        UUID memberId, String email, String address, String postcode, List<OrderItem> orderItems
) {
}
