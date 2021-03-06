package com.biciplus.backend.repositories;

import org.springframework.stereotype.Repository;

import com.biciplus.backend.model.Category;

@Repository
public interface CategoryRepository extends CustomCrudRepository<Category, Long> {
	
}
