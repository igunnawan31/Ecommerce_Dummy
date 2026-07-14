package com.example.business.cart.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.business.cart.dtos.request.CartItemCreateRequest;
import com.example.business.cart.dtos.request.CartItemUpdateRequest;
import com.example.business.cart.dtos.response.CartItemDeleteResponse;
import com.example.business.cart.dtos.response.CartItemResponse;
import com.example.business.cart.services.CartService;
import com.example.business.dtos.response.ApiResponse;

@RestController
@RequestMapping("/api/users/{userId}/cart")
public class CartController {
    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @PostMapping
    public ApiResponse<CartItemResponse> addToCart(
        @PathVariable Long userId,
        @RequestBody CartItemCreateRequest request
    ) {
        return ApiResponse.success(
            "Item added to cart successfully",
            cartService.addToCart(userId, request)
        );
    }

    @PutMapping("/{cartItemId}")
    public ApiResponse<CartItemResponse> updateCartItem(
        @PathVariable Long userId,
        @PathVariable Long cartItemId,
        @RequestBody CartItemUpdateRequest request
    ) {
        return ApiResponse.success(
            "Cart item updated successfully",
            cartService.updateCartItem(userId, cartItemId, request)
        );
    }

    @DeleteMapping("/{cartItemId}")
    public ApiResponse<CartItemDeleteResponse> removeFromCart(
        @PathVariable Long userId,
        @PathVariable Long cartItemId
    ) {
        return ApiResponse.success(
            "Cart item removed successfully",
            cartService.removeFromCart(userId, cartItemId)
        );
    }

    @GetMapping
    public ApiResponse<List<CartItemResponse>> getCart(@PathVariable Long userId) {
        return ApiResponse.success(
            "Cart fetched successfully",
            cartService.getCartByUserId(userId)
        );
    }
}
