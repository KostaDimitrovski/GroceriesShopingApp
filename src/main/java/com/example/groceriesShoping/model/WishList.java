package com.example.groceriesShoping.model;


import jakarta.persistence.*;

import java.util.List;

@Entity
public class WishList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    private User user;
    @ManyToMany
    private List<Item> itemList;
}
