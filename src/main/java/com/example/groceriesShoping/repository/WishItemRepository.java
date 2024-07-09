package com.example.groceriesShoping.repository;

import com.example.groceriesShoping.model.WishItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WishItemRepository extends JpaRepository<WishItem, Long> {
}
