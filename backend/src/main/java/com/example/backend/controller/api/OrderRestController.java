package com.example.backend.controller.api;

import com.example.backend.controller.CreateOrderRequest;
import com.example.backend.model.Email;
import com.example.backend.model.Order;
import com.example.backend.service.OrderService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class OrderRestController {

    private final OrderService orderService;

    public OrderRestController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/api/v1/orders")
    public Order createOrder(@RequestBody CreateOrderRequest createOrderRequest) {
        return orderService.createOrder(
                createOrderRequest.memberId(),
                new Email(createOrderRequest.email()),
                createOrderRequest.address(),
                createOrderRequest.postcode(),
                createOrderRequest.orderItems()
        );
    }

    @GetMapping("/api/v1/orders")
    public List<Order> getOrderList(){
        return orderService.getOrderList();
    }

    @DeleteMapping("/api/v1/orders/{orderId}")
    public UUID deleteOrder(@PathVariable UUID orderId){
        return orderService.deleteOrder(orderId);
    }

}
