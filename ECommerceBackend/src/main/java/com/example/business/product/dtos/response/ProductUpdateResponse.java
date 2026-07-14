package com.example.business.product.dtos.response;

import java.math.BigDecimal;
import java.util.List;

import com.example.business.product.enums.ProductCategories;

public class ProductUpdateResponse {
    private Long id;
    private String nameProduct;
    private BigDecimal priceProduct;
    private Integer stockProduct;
    private List<ProductImageResponse> images;
    private String message;

    public ProductUpdateResponse(
        Long id,
        String nameProduct,
        BigDecimal priceProduct,
        Integer stockProduct,
        List<ProductImageResponse> images,
        String message
    ) {
        this.id = id;
        this.nameProduct = nameProduct;
        this.priceProduct = priceProduct;
        this.stockProduct = stockProduct;
        this.images = images;
        this.message = message;
    }

    public Long getId() { return id; }
    public String getNameProduct() { return nameProduct; }
    public BigDecimal getPriceProduct() { return priceProduct; }
    public Integer getStockProduct() { return stockProduct; }
    public List<ProductImageResponse> getImages() { return images; }
    public String getMessage() { return message; }
}
