package com.biciplus.backend.repositories;

import org.springframework.stereotype.Repository;

import com.biciplus.backend.model.ShoppingCart;

@Repository
public interface ShoppingCartRepository extends CustomCrudRepository<ShoppingCart, Long> {

}
