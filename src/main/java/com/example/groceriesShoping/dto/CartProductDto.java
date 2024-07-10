package com.example.groceriesShoping.dto;

import lombok.Data;

@Data
public class CartProductDto {
    private Long id;
    private int quantity;
    private Long productId;
    private Long cartId;
}
