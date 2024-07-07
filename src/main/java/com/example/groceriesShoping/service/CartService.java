package com.example.groceriesShoping.service;


import com.example.groceriesShoping.model.Cart;

public interface CartService {
    Cart addItemToCart(Long userId, Long itemId, int quantity);
    Cart findCartByUserId(Long userId);
    Cart findByCartId(Long cartId);
}
