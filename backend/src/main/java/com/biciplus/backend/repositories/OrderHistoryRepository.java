package com.biciplus.backend.repositories;

import org.springframework.stereotype.Repository;

import com.biciplus.backend.model.OrderHistory;

@Repository
public interface OrderHistoryRepository extends CustomCrudRepository<OrderHistory, Long> {

}
