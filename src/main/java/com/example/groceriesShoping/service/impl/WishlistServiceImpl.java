package com.example.groceriesShoping.service.impl;

import com.example.groceriesShoping.model.Item;
import com.example.groceriesShoping.model.User;
import com.example.groceriesShoping.model.WishItem;
import com.example.groceriesShoping.model.Wishlist;
import com.example.groceriesShoping.repository.*;
import com.example.groceriesShoping.service.WishlistService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class WishlistServiceImpl implements WishlistService {

    private final ItemRepository itemRepository;
    private final UserRepository userRepository;
    private final WishlistRepository wishlistRepository;
    private final WishItemRepository wishItemRepository;
    public WishlistServiceImpl(ItemRepository itemRepository, UserRepository userRepository, WishlistRepository wishlistRepository, WishItemRepository wishItemRepository) {
        this.wishItemRepository = wishItemRepository;
        ;
        this.itemRepository = itemRepository;
        this.userRepository = userRepository;
        this.wishlistRepository = wishlistRepository;
    }
    @Override
    public Wishlist addItemToWishlist(Long userId, Long itemId) {
        Optional<User> userOpt = userRepository.findById(userId);
        Optional<Item> itemOpt = itemRepository.findById(itemId);

        if (userOpt.isEmpty() || itemOpt.isEmpty()) {
            throw new RuntimeException("User or Item not found");
        }

        User user = userOpt.get();
        Item item = itemOpt.get();
        Wishlist wishlist= user.getWishlist();
        if(wishlist == null){
            wishlist = new Wishlist();
            wishlist.setUser(user);
            user.setWishlist(wishlist);
        }

        Optional<WishItem> wishItemOpt = wishlist.getWishItems().stream()
                .filter(w -> w.getItem().getId().equals(itemId))
                .findFirst();
        WishItem wishItem;
        if(wishItemOpt.isPresent()){
            //TODO: exception
            wishItem = wishItemOpt.get();
        }else {
            wishItem= new WishItem();
            wishItem.setWishlist(wishlist);
            wishItem.setItem(item);
            wishlist.getWishItems().add(wishItem);
        }
        wishlistRepository.save(wishlist);
        wishItemRepository.save(wishItem);

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
