package com.example.groceriesShoping.dto;

import lombok.Data;

@Data
public class CartItemDto {
    private Long id;
    private int quantity;
    private Long itemId;
    private Long cartId;
}
