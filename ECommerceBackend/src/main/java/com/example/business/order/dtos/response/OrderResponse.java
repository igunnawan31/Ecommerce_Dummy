package com.example.business.order.dtos.response;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import com.example.business.order.enums.OrderStatus;

public class OrderResponse {
    private Long id;
    private Long userId;
    private Long shippingAddressId;
    private OrderStatus status;
    private BigDecimal totalAmount;
    private LocalDateTime createdAt;
    private List<OrderItemResponse> items;

    public OrderResponse(
        Long id,
        Long userId,
        Long shippingAddressId,
        OrderStatus status,
        BigDecimal totalAmount,
        LocalDateTime createdAt,
        List<OrderItemResponse> items
    ) {
        this.id = id;
        this.userId = userId;
        this.shippingAddressId = shippingAddressId;
        this.status = status;
        this.totalAmount = totalAmount;
        this.createdAt = createdAt;
        this.items = items;
    }

    public Long getId() { return id; }
    public Long getUserId() { return userId; }
    public Long getShippingAddressId() { return shippingAddressId; }
    public OrderStatus getStatus() { return status; }
    public BigDecimal getTotalAmount() { return totalAmount; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public List<OrderItemResponse> getItems() { return items; }
}
