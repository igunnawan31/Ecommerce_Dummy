package com.example.business.cart.dtos.response;

import java.math.BigDecimal;

public class CartItemResponse {
    private Long id;
    private Long userId;
    private Long productId;
    private String productName;
    private BigDecimal productPrice;
    private Integer quantity;
    private BigDecimal subtotal;

    public CartItemResponse(
        Long id,
        Long userId,
        Long productId,
        String productName,
        BigDecimal productPrice,
        Integer quantity,
        BigDecimal subtotal
    ) {
        this.id = id;
        this.userId = userId;
        this.productId = productId;
        this.productName = productName;
        this.productPrice = productPrice;
        this.quantity = quantity;
        this.subtotal = subtotal;
    }

    public Long getId() { return id; }
    public Long getUserId() { return userId; }
    public Long getProductId() { return productId; }
    public String getProductName() { return productName; }
    public BigDecimal getProductPrice() { return productPrice; }
    public Integer getQuantity() { return quantity; }
    public BigDecimal getSubtotal() { return subtotal; }
}
