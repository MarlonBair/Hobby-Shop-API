package com.hobbyshop.api.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hobbyshop.api.model.Purchase;
import com.hobbyshop.api.service.PurchaseService;

/**
 * Controller for endpoints for Purchase CRUD operations.
 */
@RestController
@RequestMapping("/api/purchases")
public class PurchaseController {

    private PurchaseService purchaseService;

    public PurchaseController(PurchaseService purchaseService) {
        this.purchaseService = purchaseService;
    }

    /**
     * Retrieves a Purchase by its ID.
     *
     * @param purchaseId ID of the Purchase to retrieve.
     * @return ResponseEntity containing the retrieved Purchase entity and the HTTP status.
     */
    @GetMapping("{id}")
    public ResponseEntity<Purchase> getPurchaseByPurchaseId(@PathVariable("id") Long purchaseId) {
        return new ResponseEntity<Purchase>(purchaseService.getPurchaseByPurchaseId(purchaseId), HttpStatus.OK);
    }

    /**
     * Retrieves all Purchases in the database.
     *
     * @return ResponseEntity containing List of all Purchases and HTTP status.
     */
    @GetMapping()
    public ResponseEntity<List<Purchase>> listAllPurchases() {
        return new ResponseEntity<List<Purchase>>(purchaseService.listAllPurchases(), HttpStatus.OK);
    }

    /**
     * Updates the Item associated with an existing Purchase.
     *
     * @param purchaseId ID of the Purchase to update.
     * @param itemId ID of the Item to associate with the Purchase.
     * @return ResponseEntity containing the updated Purchase entity and the HTTP status.
     */
    @PutMapping("{id}/item")
    public ResponseEntity<Purchase> updatePurchaseItemByItemId(@PathVariable("id") Long purchaseId, @RequestBody Long itemId) {
        return new ResponseEntity<Purchase>(purchaseService.updatePurchaseItemByItemId(purchaseId, itemId), HttpStatus.OK);
    }

    /**
     * Updates the User associated with an existing Purchase.
     *
     * @param purchaseId ID of the Purchase to update.
     * @param userId ID of the User to associate with the Purchase.
     * @return ResponseEntity containing the updated Purchase entity and the HTTP status.
     */
    @PutMapping("{id}/user")
    public ResponseEntity<Purchase> updatePurchaseUserByUserId(@PathVariable("id") Long purchaseId, @RequestBody Long userId) {
        return new ResponseEntity<Purchase>(purchaseService.updatePurchaseUserByUserId(purchaseId, userId), HttpStatus.OK);
    }

    /**
     * Deletes a Purchase by its ID.
     *
     * @param purchaseId ID of the Purchase to delete.
     * @return ResponseEntity with a confirmation message and the HTTP status.
     */
    @DeleteMapping("{id}")
    public ResponseEntity<String> deletePurchase(@PathVariable("id") Long purchaseId) {
        purchaseService.deletePurchase(purchaseId);
        return new ResponseEntity<String>("Purchase deleted successfully.", HttpStatus.OK);
    }

}