package com.biciplus.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.biciplus.backend.model.ShoppingCartEntry;

@Repository
public interface ShoppingCartEntryRepository extends JpaRepository<ShoppingCartEntry, Long> {

}
