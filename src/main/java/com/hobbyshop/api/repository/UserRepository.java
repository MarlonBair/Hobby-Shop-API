package com.hobbyshop.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hobbyshop.api.model.User;

/**
 * Repository interface for managing User entities.
 */
public interface UserRepository extends JpaRepository<User, Long> {
    /** Inherits all CRUD operations from JpaRepository. */
}
