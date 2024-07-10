package com.example.groceriesShoping.config;

import com.example.groceriesShoping.enums.Role;
import com.example.groceriesShoping.model.Cart;
import com.example.groceriesShoping.model.Company;
import com.example.groceriesShoping.model.Product;
import com.example.groceriesShoping.model.User;
import com.example.groceriesShoping.repository.CartRepository;
import com.example.groceriesShoping.repository.CompanyRepository;
import com.example.groceriesShoping.repository.ProductRepository;
import com.example.groceriesShoping.repository.UserRepository;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class DataLoader implements ApplicationRunner {

    private final CompanyRepository companyRepository;
    private final CartRepository cartRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final String FOLDER_PATH;

    public DataLoader(CompanyRepository companyRepository, CartRepository cartRepository, UserRepository userRepository, ProductRepository productRepository, FolderConfig folderConfig) {
        this.companyRepository = companyRepository;
        this.cartRepository = cartRepository;
        this.userRepository = userRepository;
        this.productRepository = productRepository;
        this.FOLDER_PATH = folderConfig.getFolderPath();
    }

    @Override
    @Transactional
    public void run(ApplicationArguments args) throws Exception {
        for (int i = 0; i < 5; i++) {
            User user1 = new User();
            user1.setFirstName("kosta" + i);
            user1.setLastName("dimitr" + i);
            user1.setEmail("kosta@" + i);
            user1.setPhone("12345678" + i);
            user1.setUsername("koko" + i);
            user1.setPassword("koko!" + i);
            user1.setRole(Role.USER);
            user1.setAddress("Street" + i);

            Cart cart = new Cart();
            cart.setUser(user1);  // Set the user for the cart
            user1.setCart(cart);  // Set the cart for the user

            // Save the user, which will cascade and save the cart
            userRepository.save(user1);

            Company company = new Company();
            company.setName("Company " + i);
            company.setAddress("Address " + i);
            company.setPhone("123456789" + i);
            company.setEmail("company" + i + "@example.com");
            company.setLocation("Location " + i);
            companyRepository.save(company);

            for (int j = 0; j < 5; j++) {
                Product product = new Product();
                product.setName("Product " + j + " of Company " + i);
                product.setDescription("Description of Product " + j);
                product.setPrice(10.0 * (j + 1));
                product.setDiscount("10%");
                product.setVolume(100L);
                product.setPictureUrl(FOLDER_PATH + "default.jpg");
                product.setCompany(company);
                productRepository.save(product);

                company.getProducts().add(product);
            }
            companyRepository.save(company);
        }
    }
}
