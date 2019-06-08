package com.ljx.chapter15.service;

import com.ljx.chapter15.model.Inventory;

public interface InventoryService {

    Inventory findStock(Long productId);

    Inventory findStockWithLock(Long productId);

    void decreaseInventory(Long productId,Integer quantity);

    void updateInventory(Long productId,Integer quantity);

    int decreaseInventoryWithVersion(Long productId,Integer quantity,Integer version);

    void restoreInventory(Long productId);

}
