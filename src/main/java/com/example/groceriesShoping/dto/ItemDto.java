package com.example.groceriesShoping.dto;

import com.example.groceriesShoping.model.Company;
import lombok.Data;

    @Data
    public class ItemDto {
        private Long id;
        private String name;
        private String description;
        private Long price;
        private int quantity;
        private String discount;
        private String homeAddress;
        private Long companyId;

    }
