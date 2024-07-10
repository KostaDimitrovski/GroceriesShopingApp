package com.example.groceriesShoping.service.impl;

import com.example.groceriesShoping.model.Cart;
import com.example.groceriesShoping.model.CartProduct;
import com.example.groceriesShoping.model.Product;
import com.example.groceriesShoping.model.User;
import com.example.groceriesShoping.repository.CartProductRepository;
import com.example.groceriesShoping.repository.CartRepository;
import com.example.groceriesShoping.repository.ProductRepository;
import com.example.groceriesShoping.repository.UserRepository;
import com.example.groceriesShoping.service.CartService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CartServiceImpl implements CartService {
    private final CartRepository cartRepository;
    private final CartProductRepository cartProductRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    public CartServiceImpl(CartRepository cartRepository, CartProductRepository cartProductRepository, ProductRepository productRepository, UserRepository userRepository) {
        this.cartRepository = cartRepository;
        this.cartProductRepository = cartProductRepository;
        this.productRepository = productRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Cart addProductToCart(Long userId, Long productId, int quantity) {
        Optional<User> userOpt = userRepository.findById(userId);
        Optional<Product> productOpt = productRepository.findById(productId);

        if (userOpt.isEmpty() || productOpt.isEmpty()) {
            throw new RuntimeException("User or Product not found");
        }

        User user = userOpt.get();
        Product product = productOpt.get();
        Cart cart = user.getCart();

        if (cart == null) {
            cart = new Cart();
            cart.setUser(user);
            user.setCart(cart);
        }

        Optional<CartProduct> cartProductOpt = cart.getCartProducts().stream()
                .filter(ci -> ci.getProduct().getId().equals(productId))
                .findFirst();

        CartProduct cartProduct;
        if (cartProductOpt.isPresent()) {
            cartProduct = cartProductOpt.get();
            cartProduct.setQuantity(cartProduct.getQuantity() + quantity);
        } else {
            cartProduct = new CartProduct();
            cartProduct.setCart(cart);
            cartProduct.setProduct(product);
            cartProduct.setQuantity(quantity);
            cart.getCartProducts().add(cartProduct);
        }

        cartRepository.save(cart);
        cartProductRepository.save(cartProduct);

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
