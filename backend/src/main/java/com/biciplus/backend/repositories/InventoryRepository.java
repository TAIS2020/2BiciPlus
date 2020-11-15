package com.biciplus.backend.repositories;

import org.springframework.stereotype.Repository;

import com.biciplus.backend.model.Inventory;

@Repository
public interface InventoryRepository extends CustomCrudRepository<Inventory, Long> {

}
