package com.ljx.chapter15.dao;

import com.ljx.chapter15.model.Inventory;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface InventoryDao {
    Inventory findStock(Long productId);

    Inventory findStockWithLock(Long productId);

    void decreaseInventory(@Param("productId") Long productId, @Param("quantity") Integer quantity);

    void updateInventory(@Param("productId") Long productId, @Param("currStock") Integer currStock);

    int decreaseInventoryWithVersion(@Param("productId") Long productId, @Param("quantity") Integer quantity, @Param("version") Integer version);

    void restoreInventory(@Param("productId") Long productId);
}
