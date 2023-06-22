package com.nimchynskyi.bookstorebackend.repository;

import com.nimchynskyi.bookstorebackend.model.Item;
import jakarta.annotation.Resource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
}
