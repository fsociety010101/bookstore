package com.nimchynskyi.bookstorebackend.repository;

import com.nimchynskyi.bookstorebackend.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
}
