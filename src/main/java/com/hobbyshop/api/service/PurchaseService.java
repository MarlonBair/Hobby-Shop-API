package com.hobbyshop.api.service;

import java.util.List;

import com.hobbyshop.api.model.Purchase;

public interface PurchaseService {

    Purchase createPurchase(Long userId, Long itemId);
    
    Purchase getPurchaseByPurchaseId(Long purchaseId);

    List<Purchase> listAllPurchases();

    Purchase updatePurchaseItemByItemId(Long purchaseId, Long itemId);

    Purchase updatePurchaseUserByUserId(Long purchaseId, Long userId);

    void deletePurchase(Long purchaseId);
}
