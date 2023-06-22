package com.nimchynskyi.bookstorebackend.repository;

import com.nimchynskyi.bookstorebackend.model.Order;
import com.nimchynskyi.bookstorebackend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    Optional<User> findByOrdersContaining(Order order);
}
