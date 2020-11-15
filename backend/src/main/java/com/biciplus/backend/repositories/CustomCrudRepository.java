package com.biciplus.backend.repositories;

import org.springframework.data.repository.NoRepositoryBean;

import org.springframework.data.repository.CrudRepository;

@NoRepositoryBean
public interface CustomCrudRepository<T, ID> extends CrudRepository<T, ID> {
	
	default T findEntityById(ID id) {
		return findById(id).get();
	}
}
