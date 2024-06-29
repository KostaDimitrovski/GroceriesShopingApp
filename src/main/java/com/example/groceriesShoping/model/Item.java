package com.example.groceriesShoping.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private Long price;
    private int quantity;
    private String discount;
    private String homeAddress;
    @ManyToOne
    private Company company;

}
