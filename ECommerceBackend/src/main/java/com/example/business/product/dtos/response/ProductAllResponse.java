package com.example.business.product.dtos.response;

import java.math.BigDecimal;
import java.util.List;

import com.example.business.product.enums.ProductCategories;

public class ProductAllResponse {
    private Long id;
    private String nameProduct;
    private BigDecimal priceProduct;
    private Integer stockProduct;

    public ProductAllResponse(Long id, String nameProduct, BigDecimal priceProduct, Integer stockProduct) {
        this.id = id;
        this.nameProduct = nameProduct;
        this.priceProduct = priceProduct;
        this.stockProduct = stockProduct;
    }

    public Long getId() { return id; }
    public String getNameProduct() { return nameProduct; }
    public BigDecimal getPriceProduct() { return priceProduct; }
    public Integer getStockProduct() { return stockProduct; }
}
