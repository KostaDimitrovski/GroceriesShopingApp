package com.example.groceriesShoping.service;

import com.example.groceriesShoping.dto.ProductDto;
import com.example.groceriesShoping.model.Product;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface ProductService {
    Product findProductById(Long id);
    List<Product> findAllProducts();
    Product createProduct(ProductDto productDto, MultipartFile file) throws IOException;
    Product updateProduct(Long id, ProductDto productDto);
    void deleteProduct(Long id);
}
