package com.example.business.product.dtos.request;

import java.math.BigDecimal;
import java.util.List;

import com.example.business.product.enums.ProductCategories;

public class ProductUpdateRequest {
    private String nameProduct;
    private String descriptionProduct;
    private List<ProductCategories> categoryProduct;
    private String otherCategoryProduct;
    private BigDecimal priceProduct;
    private Integer stockProduct;
    private Long storeId;

    private List<Long> imageFileIds;

    public String getNameProduct() { return nameProduct; }
    public void setNameProduct(String nameProduct) { this.nameProduct = nameProduct; }

    public String getDescriptionProduct() { return descriptionProduct; }
    public void setDescriptionProduct(String descriptionProduct) { this.descriptionProduct = descriptionProduct; }

    public List<ProductCategories> getCategoryProduct() { return categoryProduct; }
    public void setCategoryProduct(List<ProductCategories> categoryProduct) { this.categoryProduct = categoryProduct; }

    public String getOtherCategoryProduct() { return otherCategoryProduct; }
    public void setOtherCategoryProduct(String otherCategoryProduct) { this.otherCategoryProduct = otherCategoryProduct; }

    public BigDecimal getPriceProduct() { return priceProduct; }
    public void setPriceProduct(BigDecimal priceProduct) { this.priceProduct = priceProduct; }

    public Integer getStockProduct() { return stockProduct; }
    public void setStockProduct(Integer stockProduct) { this.stockProduct = stockProduct; }

    public Long getStoreId() { return storeId; }
    public void setStoreId(Long storeId) { this.storeId = storeId; }

    public List<Long> getImageFileIds() { return imageFileIds; }
    public void setImageFileIds(List<Long> imageFileIds) { this.imageFileIds = imageFileIds;}
}
