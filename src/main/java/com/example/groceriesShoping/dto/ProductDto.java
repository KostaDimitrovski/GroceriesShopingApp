package com.example.groceriesShoping.dto;

import com.example.groceriesShoping.enums.ProductType;
import lombok.Data;

    @Data
    public class ProductDto {
        private Long id;
        private String name;
        private String description;
        private Double price;
        private String discount;
        private Long volume;
        private Long companyId;
        private ProductType type;
    }
