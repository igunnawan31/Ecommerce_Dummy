package com.example.business.order.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.business.dtos.response.ApiResponse;
import com.example.business.order.dtos.request.OrderCreateRequest;
import com.example.business.order.dtos.response.OrderResponse;
import com.example.business.order.services.OrderService;

@RestController
@RequestMapping("/api/users/{userId}/orders")
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public ApiResponse<OrderResponse> createOrder(
        @PathVariable Long userId,
        @RequestBody OrderCreateRequest request
    ) {
        return ApiResponse.success(
            "Order created successfully",
            orderService.createOrderFromCart(userId, request)
        );
    }

    @GetMapping
    public ApiResponse<List<OrderResponse>> getOrders(@PathVariable Long userId) {
        return ApiResponse.success(
            "Orders fetched successfully",
            orderService.getOrdersByUserId(userId)
        );
    }

    @GetMapping("/{orderId}")
    public ApiResponse<OrderResponse> getOrderById(
        @PathVariable Long userId,
        @PathVariable Long orderId
    ) {
        return ApiResponse.success(
            "Order fetched successfully",
            orderService.getOrderById(userId, orderId)
        );
    }
}
