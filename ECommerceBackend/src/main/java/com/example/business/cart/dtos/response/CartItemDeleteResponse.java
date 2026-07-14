package com.example.business.cart.dtos.response;

public class CartItemDeleteResponse {
    private Long id;
    private String message;

    public CartItemDeleteResponse(Long id, String message) {
        this.id = id;
        this.message = message;
    }

    public Long getId() { return id; }
    public String getMessage() { return message; }
}
