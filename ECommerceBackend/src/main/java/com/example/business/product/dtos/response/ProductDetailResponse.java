package com.example.business.product.dtos.response;

import java.math.BigDecimal;
import java.util.List;

import com.example.business.product.enums.ProductCategories;

public class ProductDetailResponse {
    private Long id;
    private String nameProduct;
    private String descriptionProduct;
    private List<ProductCategories> categoryProduct;
    private String otherCategoryProduct;
    private BigDecimal priceProduct;
    private Integer stockProduct;
    private Long storeId;
    private List<ProductImageResponse> images;

    public ProductDetailResponse(
        Long id,
        String nameProduct,
        String descriptionProduct,
        List<ProductCategories> categoryProduct,
        String otherCategoryProduct,
        BigDecimal priceProduct,
        Integer stockProduct,
        Long storeId,
        List<ProductImageResponse> images
    ) {
        this.id = id;
        this.nameProduct = nameProduct;
        this.descriptionProduct = descriptionProduct;
        this.categoryProduct = categoryProduct;
        this.otherCategoryProduct = otherCategoryProduct;
        this.priceProduct = priceProduct;
        this.stockProduct = stockProduct;
        this.storeId = storeId;
        this.images = images;
    }

    public Long getId() { return id; }
    public String getNameProduct() { return nameProduct; }
    public String getDescriptionProduct() { return descriptionProduct; }
    public List<ProductCategories> getCategoryProduct() { return categoryProduct; }
    public String getOtherCategoryProduct() { return otherCategoryProduct; }
    public BigDecimal getPriceProduct() { return priceProduct; }
    public Integer getStockProduct() { return stockProduct; }
    public Long getStoreId() { return storeId; }
    public List<ProductImageResponse> getImages() { return images; }
}
