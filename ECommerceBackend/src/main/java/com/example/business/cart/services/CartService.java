package com.example.business.cart.services;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.business.cart.dtos.request.CartItemCreateRequest;
import com.example.business.cart.dtos.request.CartItemUpdateRequest;
import com.example.business.cart.dtos.response.CartItemDeleteResponse;
import com.example.business.cart.dtos.response.CartItemResponse;
import com.example.business.cart.entities.CartItem;
import com.example.business.cart.exceptions.CartItemNotFoundException;
import com.example.business.cart.repositories.CartItemRepository;
import com.example.business.order.exceptions.InsufficientStockException;
import com.example.business.product.entities.Product;
import com.example.business.product.exceptions.ProductNotFoundException;
import com.example.business.product.repositories.ProductRepository;
import com.example.business.user.services.UserService;

@Service
public class CartService {
    private final CartItemRepository cartItemRepository;
    private final ProductRepository productRepository;
    private final UserService userService;

    public CartService(
        CartItemRepository cartItemRepository,
        ProductRepository productRepository,
        UserService userService
    ) {
        this.cartItemRepository = cartItemRepository;
        this.productRepository = productRepository;
        this.userService = userService;
    }

    @Transactional
    public CartItemResponse addToCart(Long userId, CartItemCreateRequest request) {
        userService.ensureUserExists(userId);

        Product product = productRepository.findById(request.getProductId())
            .orElseThrow(() -> new ProductNotFoundException(request.getProductId()));

        int quantity = request.getQuantity() != null ? request.getQuantity() : 1;
        validateStock(product, quantity);

        CartItem cartItem = cartItemRepository
            .findByUserIdAndProductId(userId, request.getProductId())
            .orElse(null);

        if (cartItem != null) {
            int newQuantity = cartItem.getQuantity() + quantity;
            validateStock(product, newQuantity);
            cartItem.setQuantity(newQuantity);
        } else {
            cartItem = new CartItem();
            cartItem.setUserId(userId);
            cartItem.setProductId(request.getProductId());
            cartItem.setQuantity(quantity);
        }

        CartItem saved = cartItemRepository.save(cartItem);
        return toResponse(saved, product);
    }

    @Transactional
    public CartItemResponse updateCartItem(Long userId, Long cartItemId, CartItemUpdateRequest request) {
        CartItem cartItem = findCartItemForUser(userId, cartItemId);
        Product product = productRepository.findById(cartItem.getProductId())
            .orElseThrow(() -> new ProductNotFoundException(cartItem.getProductId()));

        validateStock(product, request.getQuantity());
        cartItem.setQuantity(request.getQuantity());

        CartItem updated = cartItemRepository.save(cartItem);
        return toResponse(updated, product);
    }

    @Transactional
    public CartItemDeleteResponse removeFromCart(Long userId, Long cartItemId) {
        CartItem cartItem = findCartItemForUser(userId, cartItemId);
        cartItemRepository.delete(cartItem);
        return new CartItemDeleteResponse(cartItem.getId(), "Cart item removed successfully");
    }

    @Transactional
    public void clearCart(Long userId) {
        cartItemRepository.findByUserId(userId).forEach(cartItemRepository::delete);
    }

    public List<CartItemResponse> getCartByUserId(Long userId) {
        userService.ensureUserExists(userId);
        return cartItemRepository.findByUserId(userId)
            .stream()
            .map(item -> {
                Product product = productRepository.findById(item.getProductId())
                    .orElseThrow(() -> new ProductNotFoundException(item.getProductId()));
                return toResponse(item, product);
            })
            .toList();
    }

    public List<CartItem> getCartItemsForCheckout(Long userId) {
        return cartItemRepository.findByUserId(userId);
    }

    private CartItem findCartItemForUser(Long userId, Long cartItemId) {
        return cartItemRepository.findByIdAndUserId(cartItemId, userId)
            .orElseThrow(() -> new CartItemNotFoundException(cartItemId));
    }

    private void validateStock(Product product, int quantity) {
        if (product.getStockProduct() == null || product.getStockProduct() < quantity) {
            throw new InsufficientStockException(
                product.getId(),
                quantity,
                product.getStockProduct() != null ? product.getStockProduct() : 0
            );
        }
    }

    private CartItemResponse toResponse(CartItem cartItem, Product product) {
        BigDecimal subtotal = product.getPriceProduct()
            .multiply(BigDecimal.valueOf(cartItem.getQuantity()));

        return new CartItemResponse(
            cartItem.getId(),
            cartItem.getUserId(),
            cartItem.getProductId(),
            product.getNameProduct(),
            product.getPriceProduct(),
            cartItem.getQuantity(),
            subtotal
        );
    }
}
