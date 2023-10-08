package com.hobbyshop.api.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hobbyshop.api.exception.BadRequestException;
import com.hobbyshop.api.exception.ResourceNotFoundException;
import com.hobbyshop.api.model.Item;
import com.hobbyshop.api.model.Purchase;
import com.hobbyshop.api.repository.ItemRepository;
import com.hobbyshop.api.repository.PurchaseRepository;
import com.hobbyshop.api.service.ItemService;

/**
 * Implementation for ItemService.
 */
@Service
public class ItemServiceImpl implements ItemService {

    private ItemRepository itemRepository;
    private PurchaseRepository purchaseRepository;

    public ItemServiceImpl(ItemRepository itemRepository, PurchaseRepository purchaseRepository) {
        this.itemRepository = itemRepository;
        this.purchaseRepository = purchaseRepository;
    }

    /**
     * Saves a given Item to database.
     * 
     * @param item Item to be saved.
     * @return The saved Item entity.
     */
    @Override
    @Transactional
    public Item saveItem(Item item) {
       return itemRepository.save(item);
    }

    /**
     * Retrieves an Item from database by its ID.
     * 
     * @param itemId The ID of the Item to retrieve.
     * @return The retrieved Item entity.
     * @throws ResourceNotFoundException If no Item with the given ID is found.
     */
    @Override
    public Item getItemByItemId(Long itemId) {
        return itemRepository.findById(itemId).orElseThrow(() -> 
            new ResourceNotFoundException("Item", "ID", itemId));  
    }

    /**
     * Retrieves all Items from the database.
     * 
     * @return List of all Items.
     */
    @Override
    public List<Item> listAllItems() {
        return itemRepository.findAll();
    }

    /**
     * Retrieves all Purchases of an Item by its ID.
     * 
     * @param itemId The ID of the target Item.
     * @return List of all Item Purchases.
     * @throws ResourceNotFoundException If no Item with the given ID is found.
     */
    @Override
    public List<Purchase> getAllPurchasesByItemId(Long itemId) {
        itemRepository.findById(itemId).orElseThrow(() ->
            new ResourceNotFoundException("Item", "ID", itemId));
        
        return purchaseRepository.findByItem_ItemId(itemId);
    }

    /**
     * Updates Item attributes.
     * 
     * @param newItemData The new data for the Item.
     * @param itemId The ID of the Item to update.
     * @return The updated Item entity.
     * @throws ResourceNotFoundException If no Item with the given ID is found.
     */
    @Override
    @Transactional
    public Item updateItem(Item newItemData, Long itemId) {
        Item currentItem = itemRepository.findById(itemId).orElseThrow(() ->
            new ResourceNotFoundException("Item", "ID", itemId));
        
        currentItem.setName(newItemData.getName());
        currentItem.setDescription(newItemData.getDescription());
        currentItem.setQuantity(newItemData.getQuantity());
        currentItem.setPrice(newItemData.getPrice());
       
        return itemRepository.save(currentItem);
    }

    /**
     * Updates the quantity of an item. New quantity must be between 0 and 999999 inclusive.
     * 
     * @param itemId The ID of the item to be updated.
     * @param quantityChange The change in quantity to be applied.
     * @return The item with the updated quantity.
     * @throws ResourceNotFoundException If no Item with the given ID is found.
     * @throws BadRequestException If the entered quantity change causes an invalid Item quantity.
     */
    @Override
    @Transactional
    public Item updateItemQuantity(Long itemId, Integer quantityChange) {
        Item item = itemRepository.findById(itemId).orElseThrow(() ->
            new ResourceNotFoundException("Item", "ID", itemId));

        final Integer MIN_QUANTITY = 0;
        final Integer MAX_QUANTITY = 999999;
        
        if (item.getQuantity() + quantityChange > MAX_QUANTITY || item.getQuantity() + quantityChange < MIN_QUANTITY) {
            throw new BadRequestException("updateItemQuantity", "Quantity", quantityChange);
        }

        item.setQuantity(item.getQuantity() + quantityChange);

        return itemRepository.save(item);
    }

    /**
     * Deletes an Item by its ID.
     * 
     * @param itemId The ID of the Item to delete.
     * @throws ResourceNotFoundException If no Item with the given ID is found.
     */
    @Override
    @Transactional
    public void deleteItem(Long itemId) {
        itemRepository.findById(itemId).orElseThrow(() ->
            new ResourceNotFoundException("Item", "ID", itemId));
        
        itemRepository.deleteById(itemId); 
    }
    
}
