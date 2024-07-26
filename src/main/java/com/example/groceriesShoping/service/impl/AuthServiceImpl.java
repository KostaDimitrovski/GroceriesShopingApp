package com.example.groceriesShoping.service.impl;


import com.example.groceriesShoping.controller.auth.AuthenticationRequest;
import com.example.groceriesShoping.controller.auth.AuthenticationResponse;
import com.example.groceriesShoping.controller.auth.RegisterRequest;
import com.example.groceriesShoping.dto.UserDto;
import com.example.groceriesShoping.enums.Role;
import com.example.groceriesShoping.model.Cart;
import com.example.groceriesShoping.model.User;
import com.example.groceriesShoping.model.Wishlist;
import com.example.groceriesShoping.repository.CartRepository;
import com.example.groceriesShoping.repository.UserRepository;
import com.example.groceriesShoping.repository.WishlistRepository;
import com.example.groceriesShoping.service.AuthService;
import com.example.groceriesShoping.service.JwtService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;


@Service
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final WishlistRepository wishlistRepository;
    private final CartRepository cartRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthServiceImpl(UserRepository userRepository, WishlistRepository wishlistRepository, CartRepository cartRepository, PasswordEncoder passwordEncoder, JwtService jwtService, AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.wishlistRepository = wishlistRepository;
        this.cartRepository = cartRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

    @Override
    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(),request.getPassword()));
        User user = userRepository.findByEmail(request.getEmail()).orElse(null);
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder().token(jwtToken).build();
    }

    @Override
    public AuthenticationResponse register(UserDto user) {
        User user1 = new User();
        user1.setFirstName(user.getFirstName());
        user1.setLastName(user.getLastName());
        user1.setEmail(user.getEmail());
        user1.setPhone(user.getPhone());
        user1.setPassword(passwordEncoder.encode(user.getPassword()));
        user1.setRole(Role.USER);
        user1.setAddress(user.getAddress());
        Cart cart = new Cart();
        user1.setCart(cart);
        Wishlist wishlist = new Wishlist();
        user1.setWishlist(wishlist);
        wishlistRepository.save(wishlist);
        cartRepository.save(cart);
        userRepository.save(user1);
        var jwtToken = jwtService.generateToken(user1);
        return AuthenticationResponse.builder().token(jwtToken).build();
    }


    @Override
    public AuthenticationResponse refresh(String refreshToken) {
        // Validate refresh token
        if (jwtService.isTokenExpired(refreshToken)) {
            throw new RuntimeException("Refresh token is expired");
        }

        String userEmail = jwtService.extractUserEmail(refreshToken);
        UserDetails userDetails = userRepository.findByEmail(userEmail).orElseThrow();

        String newToken = jwtService.generateToken(new HashMap<>(), userDetails);
        return new AuthenticationResponse(newToken, refreshToken);
    }
}
