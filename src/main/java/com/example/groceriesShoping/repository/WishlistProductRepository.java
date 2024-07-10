package com.example.groceriesShoping.repository;

import com.example.groceriesShoping.model.WishlistProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WishlistProductRepository extends JpaRepository<WishlistProduct, Long> {
}
