package com.ljx.chapter15.service.impl;

import com.ljx.chapter15.dao.InventoryDao;
import com.ljx.chapter15.model.Inventory;
import com.ljx.chapter15.service.InventoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class InventoryServiceImpl implements InventoryService {

    @Autowired
    private InventoryDao inventoryDao ;

    @Override
    public Inventory findStock(Long productId) {
        return inventoryDao.findStock(productId);
    }

    @Override
    public Inventory findStockWithLock(Long productId) {
        return inventoryDao.findStockWithLock(productId);
    }

    @Override
    public void decreaseInventory(Long productId, Integer quantity) {
        inventoryDao.decreaseInventory(productId,quantity);
    }

    @Override
    public void updateInventory(Long productId, Integer currStock) {
        inventoryDao.updateInventory(productId,currStock);
    }

    @Override
    public void restoreInventory(Long productId) {
        inventoryDao.restoreInventory(productId);
    }

    @Override
    public int decreaseInventoryWithVersion(Long productId, Integer quantity, Integer version) {
        int i = inventoryDao.decreaseInventoryWithVersion(productId, quantity, version);
        if(i != 1) {
            log.info("update productId:{} error ,version :{}", productId,version);
        }
        return i;
    }
}
