package com.example.business.user.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.business.user.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findById(Long id);
}
