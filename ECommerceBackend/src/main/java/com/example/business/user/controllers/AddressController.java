package com.example.business.user.controllers;

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
import com.example.business.user.dtos.request.AddressCreateRequest;
import com.example.business.user.dtos.request.AddressUpdateRequest;
import com.example.business.user.dtos.response.AddressDeleteResponse;
import com.example.business.user.dtos.response.AddressResponse;
import com.example.business.user.services.AddressService;

@RestController
@RequestMapping("/api/users/{userId}/addresses")
public class AddressController {
    private final AddressService addressService;

    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    @PostMapping
    public ApiResponse<AddressResponse> createAddress(
        @PathVariable Long userId,
        @RequestBody AddressCreateRequest request
    ) {
        return ApiResponse.success(
            "Address created successfully",
            addressService.createAddress(userId, request)
        );
    }

    @PutMapping("/{addressId}")
    public ApiResponse<AddressResponse> updateAddress(
        @PathVariable Long userId,
        @PathVariable Long addressId,
        @RequestBody AddressUpdateRequest request
    ) {
        return ApiResponse.success(
            "Address updated successfully",
            addressService.updateAddress(userId, addressId, request)
        );
    }

    @DeleteMapping("/{addressId}")
    public ApiResponse<AddressDeleteResponse> deleteAddress(
        @PathVariable Long userId,
        @PathVariable Long addressId
    ) {
        return ApiResponse.success(
            "Address deleted successfully",
            addressService.deleteAddress(userId, addressId)
        );
    }

    @GetMapping
    public ApiResponse<List<AddressResponse>> getAddresses(@PathVariable Long userId) {
        return ApiResponse.success(
            "Addresses fetched successfully",
            addressService.getAddressesByUserId(userId)
        );
    }

    @GetMapping("/{addressId}")
    public ApiResponse<AddressResponse> getAddressById(
        @PathVariable Long userId,
        @PathVariable Long addressId
    ) {
        return ApiResponse.success(
            "Address fetched successfully",
            addressService.getAddressById(userId, addressId)
        );
    }
}
