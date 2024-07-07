package com.example.groceriesShoping.dto;

import lombok.Data;

import java.util.List;

@Data
public class CartDto {
    private Long userid;
    private List<CartItemDto> cartItems;
}
