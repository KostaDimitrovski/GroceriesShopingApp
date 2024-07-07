package com.example.groceriesShoping.dto;

import com.example.groceriesShoping.model.Company;
import lombok.Data;

    @Data
    public class ItemDto {
        private Long id;
        private String name;
        private String description;
        private Double price;
        private String discount;
        private Long volume;
        private Long companyId;

    }
