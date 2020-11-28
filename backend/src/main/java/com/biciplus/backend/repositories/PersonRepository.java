package com.biciplus.backend.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.biciplus.backend.model.Person;

@Repository
public interface PersonRepository<T extends Person> extends CustomCrudRepository<T, Long> {

    @Query(value = "SELECT u FROM Person u where u.login = ?1 and u.password = ?2 ")
    T login(String username,String password);
    @Query(value = "SELECT u FROM Person u where u.token = ?1 ")
    Optional<T> findByToken(String token);
}
