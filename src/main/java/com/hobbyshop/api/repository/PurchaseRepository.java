package com.hobbyshop.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hobbyshop.api.model.Purchase;

/**
 * Repository interface for managing Purchase entities.
 */
public interface PurchaseRepository extends JpaRepository<Purchase, Long> {
    /** Inherits all CRUD operations from JpaRepository. */

    /**
     * Retrieves a list of Purchases that correspond to the item ID.
     * 
     * @param itemId ID of target item.
     * @return List of Purchases.
     */
    List<Purchase> findByItem_ItemId(Long itemId);
}
