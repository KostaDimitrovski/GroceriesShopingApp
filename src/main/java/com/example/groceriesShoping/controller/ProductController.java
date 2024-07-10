package com.example.groceriesShoping.controller;

import com.example.groceriesShoping.dto.ProductDto;
import com.example.groceriesShoping.model.Product;
import com.example.groceriesShoping.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/products")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }


    @GetMapping("/list")
    public List<Product> getProducts() {
        return productService.findAllProducts();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable Long id) {
        Product product = productService.findProductById(id);

        if(product ==null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(product);
    }

    @PostMapping("/add")
    public ResponseEntity<Product> CrateProduct(@RequestPart("product") ProductDto productDto, @RequestPart("image") MultipartFile file) throws IOException {
        Product product1 = productService.createProduct(productDto,file);
        if(product1 ==null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(product1);
    }
    @PutMapping("/edit/{id}")
    public ResponseEntity<Product> editProduct(@PathVariable Long id, @RequestBody ProductDto productDto) {
        Product product1 = productService.updateProduct(id,productDto);
        if(product1 ==null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(product1);

    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Product> deleteProduct(@PathVariable Long id) {
        Product product = productService.findProductById(id);
        if(product ==null) {
            return ResponseEntity.notFound().build();
        }else{
            productService.deleteProduct(id);
            return ResponseEntity.ok(product);
        }
    }
}
