package com.example.groceriesShoping.service;

import com.example.groceriesShoping.model.Wishlist;

public interface WishlistService {
    Wishlist addProductToWishlist(Long userId, Long productId);
    Wishlist findWishlistByUserId(Long userId);
    Wishlist findByWishlistId(Long wishlistId);
}
