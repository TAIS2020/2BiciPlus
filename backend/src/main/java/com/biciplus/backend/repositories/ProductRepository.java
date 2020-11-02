package com.biciplus.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.biciplus.backend.model.Product;

@Repository
public interface ProductRepository<T extends Product> extends JpaRepository<T, Long> {

}
