package com.hobbyshop.api.model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

/**
 * Represents a recorded purchase.
 */
@Data
@Entity
@Table(name = "purchases")
public class Purchase {
    
    /**
     * The unique ID of the purchase.
     * Generated automatically, which the PostgreSQL database will increment automatically.  
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long purchaseId;

    /**
     * The user who made the purchase.
     */
    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;

    /**
     * The target item that was purchased.
     */
    @ManyToOne
    @JoinColumn(name = "itemId")
    private Item item;

    /**
     * The date that the purchase was made.
     */
    @Column(nullable = false)
    private LocalDate purchaseDate;

}
