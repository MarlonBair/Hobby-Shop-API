package com.hobbyshop.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hobbyshop.api.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    
}
