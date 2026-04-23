package com.example.business.user.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.example.business.dtos.response.ApiResponse;
import com.example.business.user.dtos.request.UserCreateRequest;
import com.example.business.user.dtos.request.UserUpdateRequest;
import com.example.business.user.dtos.response.UserAllResponse;
import com.example.business.user.dtos.response.UserCreateResponse;
import com.example.business.user.dtos.response.UserDeleteResponse;
import com.example.business.user.dtos.response.UserDetailResponse;
import com.example.business.user.dtos.response.UserUpdateResponse;
import com.example.business.user.services.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ApiResponse<UserCreateResponse> createUser(@RequestBody UserCreateRequest request) {
        return ApiResponse.success(
            "User created successfully",
            userService.createUser(request)
        );
    }

    @PutMapping("/{id}")
    public ApiResponse<UserUpdateResponse> updateUser(@PathVariable Long id, @RequestBody UserUpdateRequest request) {
        return ApiResponse.success(
            "User updated successfully",
            userService.updateUser(id, request)
        );
    }

    @DeleteMapping("/{id}")
    public ApiResponse<UserDeleteResponse> deleteUser(@PathVariable Long id) {
        return ApiResponse.success(
            "User deleted successfully",
            userService.deleteUser(id)
        );
    }

    @GetMapping
    public ApiResponse<List<UserAllResponse>> getAllUsers() {
        return ApiResponse.success(
            "Users fetched successfully",
            userService.getAllUsers()
        );
    }

    @GetMapping("/{id}")
    public ApiResponse<UserDetailResponse> getUserById(@PathVariable Long id) {
        return ApiResponse.success(
            "User fetched successfully",
            userService.getUserById(id)
        );
    }
}
