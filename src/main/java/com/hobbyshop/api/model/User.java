package com.hobbyshop.api.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

/**
 * Represents a user/customer of the hobby shop. 
 */
@Data
@Entity
@Table(name = "users")
public class User {
    
    /**
     * The unique ID of the user.
     * Generated automatically, which the PostgreSQL database will increment automatically.  
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    /**
     * The name of user.
     */
    @Column
    private String name;

    /**
     * The email of user.
     * Necessary record and cannot be null.
     */
    @Column(nullable = false)
    private String email;

    /**
     * List of user's purchases.
     * Lazy loaded (Not fetched unless requested) for efficiency.
     * Any operations on this list will cascade to item.
     */
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Purchase> purchases = new ArrayList<>();


}
