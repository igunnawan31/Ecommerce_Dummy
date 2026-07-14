package com.example.business.product.entities;

import java.math.BigDecimal;
import java.util.List;

import com.example.business.product.enums.ProductCategories;

import jakarta.persistence.*;

@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nameProduct;

    @Column(length = 1000)
    private String descriptionProduct;

    @ElementCollection(targetClass = ProductCategories.class)
    @Enumerated(EnumType.STRING)
    private List<ProductCategories> categoryProduct;

    private String otherCategoryProduct;

    private BigDecimal priceProduct;
    private Integer stockProduct;

    private Long storeId;

    // Getter and Setter Id
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    // Getter and Setter Name
    public String getNameProduct() {
        return nameProduct;
    }

    public void setNameProduct(String nameProduct) {
        this.nameProduct = nameProduct;
    }
   
    // Getter and Setter Description
    public String getDescriptionProduct() {
        return descriptionProduct;
    }

    public void setDescriptionProduct(String descriptionProduct) {
        this.descriptionProduct = descriptionProduct;
    }

    // Getter and Setter Category
    public List<ProductCategories> getCategoryProduct() { 
        return categoryProduct; 
    }
    public void setCategoryProduct(List<ProductCategories> categoryProduct) { 
        this.categoryProduct = categoryProduct; 
    }

    public String getOtherCategoryProduct() {
        return otherCategoryProduct;
    }
    public void setOtherCategoryProduct(String otherCategoryProduct) {
        this.otherCategoryProduct = otherCategoryProduct;
    }

    // Getter and Setter Price
    public BigDecimal getPriceProduct() { 
        return priceProduct; 
    }
    public void setPriceProduct(BigDecimal priceProduct) { 
        this.priceProduct = priceProduct; 
    }

    // Getter and Setter Stock
    public Integer getStockProduct() { 
        return stockProduct; 
    }
    public void setStockProduct(Integer stockProduct) { 
        this.stockProduct = stockProduct; 
    }

    // Getter and Setter Store
    public Long getStoreId() { 
        return storeId; 
    }
    public void setStoreId(Long storeId) { 
        this.storeId = storeId; 
    }
    
    @Override
    public String toString() {
        return "Product [id=" + id + ", name=" + nameProduct + "]";
    }
}
