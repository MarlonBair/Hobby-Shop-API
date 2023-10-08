package com.hobbyshop.api.service.impl;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hobbyshop.api.exception.ResourceNotFoundException;
import com.hobbyshop.api.model.Item;
import com.hobbyshop.api.model.Purchase;
import com.hobbyshop.api.model.User;
import com.hobbyshop.api.repository.ItemRepository;
import com.hobbyshop.api.repository.PurchaseRepository;
import com.hobbyshop.api.repository.UserRepository;
import com.hobbyshop.api.service.PurchaseService;

/**
 * Implementation for PurchaseService.
 */
@Service
public class PurchaseServiceImpl implements PurchaseService{

    private PurchaseRepository purchaseRepository;
    private ItemRepository itemRepository;
    private UserRepository userRepository;

    public PurchaseServiceImpl(PurchaseRepository purchaseRepository, ItemRepository itemRepository, UserRepository userRepository) {
        this.purchaseRepository = purchaseRepository;
        this.itemRepository = itemRepository;
        this.userRepository = userRepository;
    }

    /**
     * Creates a new Purchase with the given userId and itemId.
     *
     * @param userId The ID of the User making the purchase.
     * @param itemId The ID of the Item being purchased.
     * @return The created Purchase entity.
     * @throws ResourceNotFoundException If no User or Item with the given IDs is found.
     */
    @Override
    @Transactional
    public Purchase createPurchase(Long userId, Long itemId) {
        User user = userRepository.findById(userId).orElseThrow(() ->
            new ResourceNotFoundException("User", "ID", userId));

        Item item = itemRepository.findById(itemId).orElseThrow(() ->
            new ResourceNotFoundException("Item", "ID", itemId));
        
        Purchase newPurchase = new Purchase();
        newPurchase.setUser(user);
        newPurchase.setItem(item);
        newPurchase.setPurchaseDate(LocalDate.now());

        return purchaseRepository.save(newPurchase);

    }

    /**
     * Retrieves a Purchase from the database by its ID.
     *
     * @param purchaseId The ID of the Purchase to retrieve.
     * @return The retrieved Purchase entity.
     * @throws ResourceNotFoundException If no Purchase with the given ID is found.
     */
    @Override
    public Purchase getPurchaseByPurchaseId(Long purchaseId) {
        return purchaseRepository.findById(purchaseId).orElseThrow(() ->
            new ResourceNotFoundException("Purchase", "ID", purchaseId));
    }

    /**
     * Retrieves all Purchases from the database.
     *
     * @return List of all Purchases.
     */
    @Override
    public List<Purchase> listAllPurchases() {
        return purchaseRepository.findAll();
    }

    /**
     * Updates the Item associated with a Purchase.
     *
     * @param purchaseId The ID of the Purchase to update.
     * @param itemId The ID of the new Item to associate with the Purchase.
     * @return The updated Purchase entity.
     * @throws ResourceNotFoundException If no Purchase or Item with the given IDs is found.
     */
    @Override
    @Transactional
    public Purchase updatePurchaseItemByItemId(Long purchaseId, Long itemId) {
        Purchase currentPurchase = purchaseRepository.findById(purchaseId).orElseThrow(() ->
            new ResourceNotFoundException("Purchase", "ID", purchaseId));

        Item item = itemRepository.findById(itemId).orElseThrow(() ->
            new ResourceNotFoundException("Item", "ID", itemId));

        currentPurchase.setItem(item);

        return purchaseRepository.save(currentPurchase);
    }

    /**
     * Updates the User associated with a Purchase.
     *
     * @param purchaseId The ID of the Purchase to update.
     * @param userId The ID of the new User to associate with the Purchase.
     * @return The updated Purchase entity.
     * @throws ResourceNotFoundException If no Purchase or User with the given IDs is found.
     */
    @Override
    @Transactional
    public Purchase updatePurchaseUserByUserId(Long purchaseId, Long userId) {
        Purchase currentPurchase = purchaseRepository.findById(purchaseId).orElseThrow(() ->
            new ResourceNotFoundException("Purchase", "ID", purchaseId));

        User user = userRepository.findById(userId).orElseThrow(() ->
            new ResourceNotFoundException("User", "ID", userId));

        currentPurchase.setUser(user);

        return purchaseRepository.save(currentPurchase);
    }

    /**
     * Deletes a Purchase by its ID.
     *
     * @param purchaseId The ID of the Purchase to delete.
     * @throws ResourceNotFoundException If no Purchase with the given ID is found.
     */
    @Override
    @Transactional
    public void deletePurchase(Long purchaseId) {
        purchaseRepository.findById(purchaseId).orElseThrow(() ->
            new ResourceNotFoundException("Purchase", "ID", purchaseId));
        
        purchaseRepository.deleteById(purchaseId); 
    }
    
}
