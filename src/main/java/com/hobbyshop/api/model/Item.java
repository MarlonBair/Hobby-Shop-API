package com.hobbyshop.api.model;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

/**
 * Represents an item in the hobby shop inventory.
 */
@Data
@Entity
@Table(name = "items")
public class Item {

    /**
     * The unique ID of the item.
     * Generated automatically, which the PostgreSQL database will increment automatically.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long itemId;

    /**
     * The name of the item.
     * Necessary record and cannot be null.
     */
    @Column(nullable = false)
    private String name;

    /**
     * The description of the item.
     */
    @Column
    private String description;

    /**
     * The quantity of the item in stock.
     * Necessary record and cannot be null.
     */
    @Column(nullable = false)
    private Integer quantity;

    /**
     * The price of the item, with a 1:1 ratio to USD.
     * Due to the precision and scale of the BigDecimal, the maximum value is 9999999.99.
     */
    @Column(precision = 9, scale = 2) 
    private BigDecimal price;
    
}
