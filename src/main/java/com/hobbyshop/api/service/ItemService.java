package com.hobbyshop.api.service;

import java.util.List;

import com.hobbyshop.api.model.Item;
import com.hobbyshop.api.model.Purchase;

public interface ItemService {

    Item saveItem(Item item);

    Item getItemByItemId(Long itemId);

    List<Item> listAllItems();

    List<Purchase> getAllPurchasesByItemId(Long itemId);   

    Item updateItem(Item newItemData, Long itemId);

    Item updateItemQuantity(Long itemId, Integer quantityChange);

    void deleteItem(Long itemId);
}
