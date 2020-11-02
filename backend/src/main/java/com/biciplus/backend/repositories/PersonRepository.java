package com.biciplus.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.biciplus.backend.model.Person;

@Repository
public interface PersonRepository<T extends Person> extends JpaRepository<T, Long> {

}
