package com.example.groceriesShoping.service;

import com.example.groceriesShoping.model.Wishlist;

public interface WishlistService {
    Wishlist addItemToWishlist(Long userId, Long itemId);
    Wishlist findWishlistByUserId(Long userId);
    Wishlist findByWishlistId(Long wishlistId);
}
