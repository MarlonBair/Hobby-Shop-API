package com.hobbyshop.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hobbyshop.api.model.Purchase;

public interface PurchaseRepository extends JpaRepository<Purchase, Long> {
    
}
