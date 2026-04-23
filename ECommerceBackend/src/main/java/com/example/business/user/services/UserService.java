package com.example.business.user.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.business.user.dtos.request.UserCreateRequest;
import com.example.business.user.dtos.request.UserUpdateRequest;
import com.example.business.user.dtos.response.UserAllResponse;
import com.example.business.user.dtos.response.UserCreateResponse;
import com.example.business.user.dtos.response.UserDeleteResponse;
import com.example.business.user.dtos.response.UserDetailResponse;
import com.example.business.user.dtos.response.UserUpdateResponse;
import com.example.business.user.entities.User;
import com.example.business.user.repositories.UserRepository;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // Create User
    public UserCreateResponse createUser(UserCreateRequest request) {
        User user = new User();
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());

        User saved = userRepository.save(user);

        return new UserCreateResponse(
            saved.getId(),
            saved.getName(),
            saved.getEmail(),
            "User created successfully"
        );
    }

    // Update User
    public UserUpdateResponse updateUser(Long id, UserUpdateRequest request) {
        User user = userRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("User not found"));

        if (request.getName() != null) {
            user.setName(request.getName());
        }

        User updated = userRepository.save(user);

        return new UserUpdateResponse(
            updated.getId(),
            updated.getName(),
            "User updated successfully"
        );
    }

    // Delete User
    public UserDeleteResponse deleteUser(Long id) {
        User user = userRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("User not found"));

        userRepository.delete(user);

        return new UserDeleteResponse(
            user.getId(),
            "User deleted successfully"
        );
    }

    // Get All User
    public List<UserAllResponse> getAllUsers() {
        return userRepository.findAll()
            .stream()
            .map(user -> new UserAllResponse(
                user.getId(), 
                user.getName(),
                user.getEmail()
            ))
            .toList();
    }

    //
    public UserDetailResponse getUserById(Long id) {
        User user = userRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("User not found"));

        return new UserDetailResponse(
            user.getId(),
            user.getName(),
            user.getEmail()
        );
    }
}
