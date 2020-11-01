package com.biciplus.backend.repositories;

import org.springframework.stereotype.Repository;

import com.biciplus.backend.model.Inventory;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory, Long> {

}
