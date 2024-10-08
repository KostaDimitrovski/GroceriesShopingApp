package com.example.groceriesShoping.controller;

import com.example.groceriesShoping.model.Cart;
import com.example.groceriesShoping.service.CartService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cart")
public class CartController {
    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }
    @PostMapping("/{userId}/add")
    public Cart addProductToCart(@PathVariable Long userId, @RequestParam Long productId, @RequestParam int quantity) {
        return cartService.addProductToCart(userId, productId, quantity);
    }
    @GetMapping("/user/{id}")
    public Cart getCartByUserId(@PathVariable Long id) {
        return cartService.findCartByUserId(id);
    }
    @GetMapping("/{id}")
    public Cart getCartById(@PathVariable Long id) {
        return cartService.findByCartId(id);
    }

}
