package com.hobbyshop.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hobbyshop.api.model.Item;

/**
 * Repository interface for managing Item entities.
 */
public interface ItemRepository extends JpaRepository<Item, Long> {
    /** Inherits all CRUD operations from JpaRepository. */
}
