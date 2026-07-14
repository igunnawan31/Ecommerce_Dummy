package com.example.business.product.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.business.dtos.response.ApiResponse;
import com.example.business.product.dtos.request.ProductCreateRequest;
import com.example.business.product.dtos.request.ProductUpdateRequest;
import com.example.business.product.dtos.response.ProductAllResponse;
import com.example.business.product.dtos.response.ProductCreateResponse;
import com.example.business.product.dtos.response.ProductDeleteResponse;
import com.example.business.product.dtos.response.ProductDetailResponse;
import com.example.business.product.dtos.response.ProductUpdateResponse;
import com.example.business.product.services.ProductService;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public ApiResponse<ProductCreateResponse> createProduct(@RequestBody ProductCreateRequest request) {
        return ApiResponse.success(
            "Product created successfully",
            productService.createProduct(request)
        );
    }

    @PutMapping("/{id}")
    public ApiResponse<ProductUpdateResponse> updateProduct(
        @PathVariable Long id,
        @RequestBody ProductUpdateRequest request
    ) {
        return ApiResponse.success(
            "Product updated successfully",
            productService.updateProduct(id, request)
        );
    }

    @DeleteMapping("/{id}")
    public ApiResponse<ProductDeleteResponse> deleteProduct(@PathVariable Long id) {
        return ApiResponse.success(
            "Product deleted successfully",
            productService.deleteProduct(id)
        );
    }

    @GetMapping
    public ApiResponse<List<ProductAllResponse>> getAllProducts() {
        return ApiResponse.success(
            "Products fetched successfully",
            productService.getAllProducts()
        );
    }

    @GetMapping("/{id}")
    public ApiResponse<ProductDetailResponse> getProductById(@PathVariable Long id) {
        return ApiResponse.success(
            "Product fetched successfully",
            productService.getProductById(id)
        );
    }
}
