package com.biciplus.backend.repositories;

import org.springframework.stereotype.Repository;

import com.biciplus.backend.model.Question;

@Repository
public interface QuestionRepository extends CustomCrudRepository<Question, Long> {

}
