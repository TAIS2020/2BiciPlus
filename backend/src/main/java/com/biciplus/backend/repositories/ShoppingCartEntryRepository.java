package com.biciplus.backend.repositories;

import org.springframework.stereotype.Repository;

import com.biciplus.backend.model.ShoppingCartEntry;

@Repository
public interface ShoppingCartEntryRepository extends CustomCrudRepository<ShoppingCartEntry, Long> {

}
