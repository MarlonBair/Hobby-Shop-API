package com.hobbyshop.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hobbyshop.api.model.Purchase;

/**
 * Repository interface for managing Purchase entities.
 */
public interface PurchaseRepository extends JpaRepository<Purchase, Long> {
    /** Inherits all CRUD operations from JpaRepository. */
}
