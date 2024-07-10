package com.example.groceriesShoping.service.impl;

import com.example.groceriesShoping.model.Product;
import com.example.groceriesShoping.model.User;
import com.example.groceriesShoping.model.WishlistProduct;
import com.example.groceriesShoping.model.Wishlist;
import com.example.groceriesShoping.repository.*;
import com.example.groceriesShoping.service.WishlistService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class WishlistServiceImpl implements WishlistService {

    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    private final WishlistRepository wishlistRepository;
    private final WishlistProductRepository wishlistProductRepository;
    public WishlistServiceImpl(ProductRepository productRepository, UserRepository userRepository, WishlistRepository wishlistRepository, WishlistProductRepository wishlistProductRepository) {
        this.wishlistProductRepository = wishlistProductRepository;
        ;
        this.productRepository = productRepository;
        this.userRepository = userRepository;
        this.wishlistRepository = wishlistRepository;
    }
    @Override
    public Wishlist addProductToWishlist(Long userId, Long productId) {
        Optional<User> userOpt = userRepository.findById(userId);
        Optional<Product> productOpt = productRepository.findById(productId);

        if (userOpt.isEmpty() || productOpt.isEmpty()) {
            throw new RuntimeException("User or Product not found");
        }

        User user = userOpt.get();
        Product product = productOpt.get();
        Wishlist wishlist= user.getWishlist();
        if(wishlist == null){
            wishlist = new Wishlist();
            wishlist.setUser(user);
            user.setWishlist(wishlist);
        }

        Optional<WishlistProduct> wishlistProduct1 = wishlist.getWishlistProducts().stream()
                .filter(w -> w.getProduct().getId().equals(productId))
                .findFirst();
        WishlistProduct wishlistProduct;
        if(wishlistProduct1.isPresent()){
            //TODO: exception
            wishlistProduct = wishlistProduct1.get();
        }else {
            wishlistProduct = new WishlistProduct();
            wishlistProduct.setWishlist(wishlist);
            wishlistProduct.setProduct(product);
            wishlist.getWishlistProducts().add(wishlistProduct);
        }
        wishlistRepository.save(wishlist);
        wishlistProductRepository.save(wishlistProduct);

        return wishlist;

    }

    @Override
    public Wishlist findWishlistByUserId(Long userId) {
        User user = userRepository.findById(userId).orElseThrow();
        return user.getWishlist();
    }

    @Override
    public Wishlist findByWishlistId(Long wishlistId) {
        return wishlistRepository.findById(wishlistId).orElseThrow();
    }
}
