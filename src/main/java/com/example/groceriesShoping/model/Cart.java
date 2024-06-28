package com.example.groceriesShoping.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int userId;
    @OneToMany
    private List<Item> products;
    private Long totalCost;

}
