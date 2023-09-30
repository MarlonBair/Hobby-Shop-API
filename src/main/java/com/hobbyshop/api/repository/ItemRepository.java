package com.hobbyshop.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hobbyshop.api.model.Item;

public interface ItemRepository extends JpaRepository<Item, Long> {
    
}
