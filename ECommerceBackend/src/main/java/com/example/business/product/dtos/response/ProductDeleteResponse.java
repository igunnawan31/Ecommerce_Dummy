package com.example.business.product.dtos.response;

public class ProductDeleteResponse {
    private Long id;
    private String message;

    public ProductDeleteResponse(Long id, String message) {
        this.id = id;
        this.message = message;
    }

    public Long getId() { return id; }
    public String getMessage() { return message; }
}
