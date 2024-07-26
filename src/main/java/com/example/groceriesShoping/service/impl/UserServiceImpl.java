package com.example.groceriesShoping.service.impl;

import com.example.groceriesShoping.dto.UserDto;
import com.example.groceriesShoping.enums.Role;
import com.example.groceriesShoping.model.Cart;
import com.example.groceriesShoping.model.User;
import com.example.groceriesShoping.model.Wishlist;
import com.example.groceriesShoping.repository.CartRepository;
import com.example.groceriesShoping.repository.UserRepository;
import com.example.groceriesShoping.repository.WishlistRepository;
import com.example.groceriesShoping.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final CartRepository cartRepository;
    private final WishlistRepository wishlistRepository;

    public UserServiceImpl(UserRepository userRepository, CartRepository cartRepository, WishlistRepository wishlistRepository) {
        this.userRepository = userRepository;
        this.cartRepository = cartRepository;
        this.wishlistRepository = wishlistRepository;
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User findById(Long id) {
        // TODO: napravi exceptions
        return userRepository.findById(id).orElseThrow();
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow();
    }


    @Override
    public User addUser(UserDto user) {
        User user1=new User();
        user1.setFirstName(user.getFirstName());
        user1.setLastName(user.getLastName());
        user1.setEmail(user.getEmail());
        user1.setPhone(user.getPhone());
        user1.setPassword(user.getPassword());
        user1.setRole(user.getRole());
        user1.setAddress(user.getAddress());
        Cart cart=new Cart();
        user1.setCart(cart);
        Wishlist wishlist=new Wishlist();
        user1.setWishlist(wishlist);
        wishlistRepository.save(wishlist);
        cartRepository.save(cart);
        userRepository.save(user1);
        return user1;
    }

    @Override
    public User updateUser(Long id, UserDto user) {
        User user1=userRepository.findById(id).orElseThrow();
        user1.setFirstName(user.getFirstName());
        user1.setLastName(user.getLastName());
        user1.setEmail(user.getEmail());
        user1.setPhone(user.getPhone());
        user1.setPassword(user.getPassword());
        user1.setRole(Role.USER);
        user1.setAddress(user.getAddress());

        userRepository.save(user1);
        return user1;
    }

    @Override
    public void deleteById(Long id) {
        User user= userRepository.findById(id).orElseThrow();
        userRepository.delete(user);
    }

    @Override
    public User register(String password, String repeatPassword, String name, String surname, Role role) {
        return null;
    }
}
