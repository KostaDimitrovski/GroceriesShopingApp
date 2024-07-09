package com.example.groceriesShoping.controller;

import com.example.groceriesShoping.model.Wishlist;
import com.example.groceriesShoping.service.WishlistService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/wishlist")
public class WishlistController {

    private final WishlistService wishlistService;

    public WishlistController(WishlistService wishlistService) {
        this.wishlistService = wishlistService;
    }

    @PostMapping("/{userId}/add")
    public Wishlist addItemToWishlist(@PathVariable Long userId, @RequestParam Long itemId) {
        return wishlistService.addItemToWishlist(userId,itemId);
    }

    @GetMapping("/user/{id}")
    public Wishlist getCartByUserId(@PathVariable Long id) {
        return wishlistService.findWishlistByUserId(id);
    }
    @GetMapping("/{id}")
    public Wishlist getCartById(@PathVariable Long id) {
        return wishlistService.findByWishlistId(id);
    }
}
