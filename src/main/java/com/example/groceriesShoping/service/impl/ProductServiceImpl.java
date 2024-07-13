package com.example.groceriesShoping.service.impl;

import com.example.groceriesShoping.dto.ProductDto;
import com.example.groceriesShoping.model.Company;
import com.example.groceriesShoping.model.Product;
import com.example.groceriesShoping.repository.CompanyRepository;
import com.example.groceriesShoping.repository.ProductRepository;
import com.example.groceriesShoping.service.FileService;
import com.example.groceriesShoping.service.ProductService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final CompanyRepository companyRepository;
    private final FileService fileService;

    public ProductServiceImpl(ProductRepository productRepository, CompanyRepository companyRepository, FileService fileService) {
        this.productRepository = productRepository;
        this.companyRepository = companyRepository;
        this.fileService = fileService;
    }

    @Override
    public Product findProductById(Long id) {
        return productRepository.findById(id).orElseThrow();
    }

    @Override
    public List<Product> findAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product createProduct(ProductDto productDto, MultipartFile file) throws IOException {
        Product product = new Product();
        String uploadImage = fileService.uploadImageToFileSystem(file);
//        byte[] imageData=fileService.downloadImageFromFileSystem(uploadImage);
        product.setType(productDto.getType());
        product.setPictureUrl(uploadImage);
        product.setName(productDto.getName());
        product.setPrice(productDto.getPrice());
        Company company=companyRepository.findById(productDto.getCompanyId()).orElseThrow();
        product.setCompany(company);
        product.setDescription(productDto.getDescription());
        product.setDiscount(productDto.getDiscount());
        product.setVolume(productDto.getVolume());
        productRepository.save(product);
        List<Product> products =company.getProducts();
        products.add(product);
        company.setProducts(products);
        companyRepository.save(company);
        return product;
    }

    @Override
    public Product updateProduct(Long id, ProductDto productDto) {
        Product product = findProductById(id);
        product.setName(productDto.getName());
        product.setPrice(productDto.getPrice());
        product.setCompany(companyRepository.findById(productDto.getCompanyId()).orElseThrow());
        product.setDescription(productDto.getDescription());
        product.setDiscount(productDto.getDiscount());

        return productRepository.save(product);
    }

    @Override
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }
}
