package com.example.groceriesShoping.service;


import com.example.groceriesShoping.model.Cart;

public interface CartService {
    Cart addProductToCart(Long userId, Long productId, int quantity);
    Cart findCartByUserId(Long userId);
    Cart findByCartId(Long cartId);
}
