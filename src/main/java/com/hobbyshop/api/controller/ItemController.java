package com.hobbyshop.api.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hobbyshop.api.model.Item;
import com.hobbyshop.api.model.Purchase;
import com.hobbyshop.api.service.ItemService;

/**
 * Controller for endpoints for Item CRUD operations.
 */
@RestController
@RequestMapping("/api/items")
public class ItemController {
    
    private ItemService itemService;

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    /**
     * Saves a new Item to the database.
     * 
     * @param item The Item entity to be saved.
     * @return ResponseEntity containing the saved Item entity and the HTTP status.
     */
    @PostMapping()
    public ResponseEntity<Item> saveItem(@RequestBody Item item) {
        return new ResponseEntity<Item>(itemService.saveItem(item), HttpStatus.CREATED);
    }

    /**
     * Retrieves an Item by its ID.
     * 
     * @param itemId The ID of the Item to retrieve.
     * @return ResponseEntity containing the retrieved Item entity and the HTTP status.
     */
    @GetMapping("{id}")
    public ResponseEntity<Item> getItemByItemId(@PathVariable("id") Long itemId ) {
        return new ResponseEntity<Item>(itemService.getItemByItemId(itemId), HttpStatus.OK);
    }

    /**
     * Retrieves all Purchases for an Item by its ID.
     * 
     * @param itemId The ID of the Item whose Purchases to retrieve.
     * @return ResponseEntity containing List of Purchases and the HTTP status.
     */
    @GetMapping("{id}/purchases")
    public ResponseEntity<List<Purchase>> getPurchasesByItemId(@PathVariable("id") Long itemId) {
        return new ResponseEntity<List<Purchase>>(itemService.getAllPurchasesByItemId(itemId), HttpStatus.OK);
    }

    /**
     * Retrieves all Items in database.
     * 
     * @return ResponseEntity containing List of all Items and HTTP status.
     */
    @GetMapping()
    public ResponseEntity<List<Item>> listAllItems() {
        return new ResponseEntity<List<Item>>(itemService.listAllItems(), HttpStatus.OK);
    }

    /**
     * Updates an existing Item by its ID.
     * 
     * @param itemId ID of Item to update.
     * @param item Updated Item entity.
     * @return ResponseEntity containing updated Item and HTTP status.
     */
    @PutMapping("{id}")
    public ResponseEntity<Item> updateItem(@PathVariable("id") Long itemId, @RequestBody Item item) {
        return new ResponseEntity<Item>(itemService.updateItem(item, itemId), HttpStatus.OK);
    }

    /**
     * Updates the quantity of Item by a given amount.
     * 
     * @param itemId ID of Item to update quantity.
     * @param quantityChange Integer value of amount to be added to Item's quantity.
     * @return ResponseEntity containing updated Item and HTTP status. 
     */
    @PatchMapping("{id}/quantity")
    public ResponseEntity<Item> updateItemQuantity(@PathVariable("id") Long itemId, @RequestBody Integer quantityChange) {
        return new ResponseEntity<Item>(itemService.updateItemQuantity(itemId, quantityChange), HttpStatus.OK);
    }

    /**
     * Deletes an Item by its ID.
     * 
     * @param itemId ID of Item to delete.
     * @return ResponseEntity containing String confirmation of Item deletion and HTTP status.
     */
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteItem(@PathVariable("id") Long itemId) {
        itemService.deleteItem(itemId);
        return new ResponseEntity<String>("Item deleted successfully.", HttpStatus.OK);
    }
}
