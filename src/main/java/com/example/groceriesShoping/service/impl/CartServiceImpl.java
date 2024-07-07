package com.example.groceriesShoping.service.impl;

import com.example.groceriesShoping.model.Cart;
import com.example.groceriesShoping.model.CartItem;
import com.example.groceriesShoping.model.Item;
import com.example.groceriesShoping.model.User;
import com.example.groceriesShoping.repository.CartItemRepository;
import com.example.groceriesShoping.repository.CartRepository;
import com.example.groceriesShoping.repository.ItemRepository;
import com.example.groceriesShoping.repository.UserRepository;
import com.example.groceriesShoping.service.CartService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CartServiceImpl implements CartService {
    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private final ItemRepository itemRepository;
    private final UserRepository userRepository;

    public CartServiceImpl(CartRepository cartRepository, CartItemRepository cartItemRepository, ItemRepository itemRepository, UserRepository userRepository) {
        this.cartRepository = cartRepository;
        this.cartItemRepository = cartItemRepository;
        this.itemRepository = itemRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Cart addItemToCart(Long userId, Long itemId, int quantity) {
        Optional<User> userOpt = userRepository.findById(userId);
        Optional<Item> itemOpt = itemRepository.findById(itemId);

        if (userOpt.isEmpty() || itemOpt.isEmpty()) {
            throw new RuntimeException("User or Item not found");
        }

        User user = userOpt.get();
        Item item = itemOpt.get();
        Cart cart = user.getCart();

        if (cart == null) {
            cart = new Cart();
            cart.setUser(user);
            user.setCart(cart);
        }

        Optional<CartItem> cartItemOpt = cart.getCartItems().stream()
                .filter(ci -> ci.getItem().getId().equals(itemId))
                .findFirst();

        CartItem cartItem;
        if (cartItemOpt.isPresent()) {
            cartItem = cartItemOpt.get();
            cartItem.setQuantity(cartItem.getQuantity() + quantity);
        } else {
            cartItem = new CartItem();
            cartItem.setCart(cart);
            cartItem.setItem(item);
            cartItem.setQuantity(quantity);
            cart.getCartItems().add(cartItem);
        }

        cartRepository.save(cart);
        cartItemRepository.save(cartItem);

        return cart;
    }

    @Override
    public Cart findCartByUserId(Long userId) {
        User user= userRepository.findById(userId).get();
        return user.getCart();
    }

    @Override
    public Cart findByCartId(Long cartId) {
        return cartRepository.findById(cartId).orElseThrow();
    }
}
