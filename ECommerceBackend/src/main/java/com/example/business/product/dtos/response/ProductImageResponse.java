package com.example.business.product.dtos.response;

public class ProductImageResponse {
    private Long id;
    private String filePath;

    public ProductImageResponse(Long id, String filePath) {
        this.id = id;
        this.filePath = filePath;
    }

    public Long getId() {
        return id;
    }

    public String getFilePath() {
        return filePath;
    }
}
