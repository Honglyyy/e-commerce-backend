package com.ly.inventoryservice.repository;

import com.ly.inventoryservice.model.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory,Long> {
    boolean existsBySkuAndQuantityIsGreaterThanEqual(String sku, Integer quantity);
}
