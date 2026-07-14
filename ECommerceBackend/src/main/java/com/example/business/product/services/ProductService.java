package com.example.business.product.services;

import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.business.files.entities.File;
import com.example.business.files.enums.ReferenceType;
import com.example.business.files.repositories.FilesRepository;
import com.example.business.product.dtos.request.ProductCreateRequest;
import com.example.business.product.dtos.request.ProductUpdateRequest;
import com.example.business.product.dtos.response.ProductAllResponse;
import com.example.business.product.dtos.response.ProductCreateResponse;
import com.example.business.product.dtos.response.ProductDeleteResponse;
import com.example.business.product.dtos.response.ProductDetailResponse;
import com.example.business.product.dtos.response.ProductImageResponse;
import com.example.business.product.dtos.response.ProductUpdateResponse;
import com.example.business.product.entities.Product;
import com.example.business.product.exceptions.ProductNotFoundException;
import com.example.business.product.repositories.ProductRepository;

@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final FilesRepository filesRepository;

    public ProductService(ProductRepository productRepository, FilesRepository filesRepository) {
        this.productRepository = productRepository;
        this.filesRepository = filesRepository;
    }

    @Transactional
    public ProductCreateResponse createProduct(ProductCreateRequest request) {
        Product product = new Product();
        product.setNameProduct(request.getNameProduct());
        product.setDescriptionProduct(request.getDescriptionProduct());
        product.setCategoryProduct(request.getCategoryProduct());
        product.setOtherCategoryProduct(request.getOtherCategoryProduct());
        product.setPriceProduct(request.getPriceProduct());
        product.setStockProduct(request.getStockProduct());
        product.setStoreId(request.getStoreId());

        Product saved = productRepository.save(product);
        List<ProductImageResponse> imageResponses = linkProductImages(saved.getId(), request.getImageFileIds());

        return new ProductCreateResponse(
            saved.getId(),
            saved.getNameProduct(),
            saved.getDescriptionProduct(),
            saved.getCategoryProduct(),
            saved.getOtherCategoryProduct(),
            saved.getPriceProduct(),
            saved.getStockProduct(),
            imageResponses,
            "Product created successfully"
        );
    }

    @Transactional
    public ProductUpdateResponse updateProduct(Long id, ProductUpdateRequest request) {
        Product product = productRepository.findById(id)
            .orElseThrow(() -> new ProductNotFoundException(id));

        if (request.getNameProduct() != null) {
            product.setNameProduct(request.getNameProduct());
        }
        if (request.getDescriptionProduct() != null) {
            product.setDescriptionProduct(request.getDescriptionProduct());
        }
        if (request.getCategoryProduct() != null) {
            product.setCategoryProduct(request.getCategoryProduct());
        }
        if (request.getOtherCategoryProduct() != null) {
            product.setOtherCategoryProduct(request.getOtherCategoryProduct());
        }
        if (request.getPriceProduct() != null) {
            product.setPriceProduct(request.getPriceProduct());
        }
        if (request.getStockProduct() != null) {
            product.setStockProduct(request.getStockProduct());
        }
        if (request.getStoreId() != null) {
            product.setStoreId(request.getStoreId());
        }

        Product updated = productRepository.save(product);
        List<ProductImageResponse> imageResponses = request.getImageFileIds() != null
            ? linkProductImages(updated.getId(), request.getImageFileIds())
            : getProductImages(updated.getId());

        return new ProductUpdateResponse(
            updated.getId(),
            updated.getNameProduct(),
            updated.getPriceProduct(),
            updated.getStockProduct(),
            imageResponses,
            "Product updated successfully"
        );
    }

    @Transactional
    public ProductDeleteResponse deleteProduct(Long id) {
        Product product = productRepository.findById(id)
            .orElseThrow(() -> new ProductNotFoundException(id));

        productRepository.delete(product);

        return new ProductDeleteResponse(product.getId(), "Product deleted successfully");
    }

    public List<ProductAllResponse> getAllProducts() {
        return productRepository.findAll()
            .stream()
            .map(product -> new ProductAllResponse(
                product.getId(),
                product.getNameProduct(),
                product.getPriceProduct(),
                product.getStockProduct()
            ))
            .toList();
    }

    public ProductDetailResponse getProductById(Long id) {
        Product product = productRepository.findById(id)
            .orElseThrow(() -> new ProductNotFoundException(id));

        return new ProductDetailResponse(
            product.getId(),
            product.getNameProduct(),
            product.getDescriptionProduct(),
            product.getCategoryProduct(),
            product.getOtherCategoryProduct(),
            product.getPriceProduct(),
            product.getStockProduct(),
            product.getStoreId(),
            getProductImages(product.getId())
        );
    }

    private List<ProductImageResponse> linkProductImages(Long productId, List<Long> imageFileIds) {
        if (imageFileIds == null || imageFileIds.isEmpty()) {
            return Collections.emptyList();
        }

        List<File> images = filesRepository.findAllById(imageFileIds);
        for (File image : images) {
            image.setReferenceId(productId);
            image.setReferenceType(ReferenceType.PRODUCT);
        }
        filesRepository.saveAll(images);

        return images.stream()
            .map(image -> new ProductImageResponse(image.getId(), image.getFilePath()))
            .toList();
    }

    private List<ProductImageResponse> getProductImages(Long productId) {
        return filesRepository.findFirstByReferenceIdAndReferenceType(productId, ReferenceType.PRODUCT)
            .stream()
            .map(image -> new ProductImageResponse(image.getId(), image.getFilePath()))
            .toList();
    }
}
