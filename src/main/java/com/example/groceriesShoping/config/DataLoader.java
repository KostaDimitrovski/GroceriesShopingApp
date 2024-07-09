package com.example.groceriesShoping.config;

import com.example.groceriesShoping.enums.Role;
import com.example.groceriesShoping.model.Cart;
import com.example.groceriesShoping.model.Company;
import com.example.groceriesShoping.model.Item;
import com.example.groceriesShoping.model.User;
import com.example.groceriesShoping.repository.CartRepository;
import com.example.groceriesShoping.repository.CompanyRepository;
import com.example.groceriesShoping.repository.ItemRepository;
import com.example.groceriesShoping.repository.UserRepository;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements ApplicationRunner {

    private final CompanyRepository companyRepository;
    private final CartRepository cartRepository;
    private final UserRepository userRepository;
    private final ItemRepository itemRepository;
    private final String FOLDER_PATH;

    public DataLoader(CompanyRepository companyRepository, CartRepository cartRepository, UserRepository userRepository, ItemRepository itemRepository, FolderConfig folderConfig) {
        this.companyRepository = companyRepository;
        this.cartRepository = cartRepository;
        this.userRepository = userRepository;
        this.itemRepository = itemRepository;
        FOLDER_PATH = folderConfig.getFolderPath();
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        // Create 5 companies
        for (int i = 0; i < 5; i++) {
            User user1=new User();
            user1.setFirstName("kosta"+i);
            user1.setLastName("dimitr"+i);
            user1.setEmail("kosta@"+i);
            user1.setPhone("12345678"+i);
            user1.setUsername("koko"+i);
            user1.setPassword("koko!"+i);
            user1.setRole(Role.valueOf("USER"));
            user1.setAddress("Street"+i);
            Cart cart=new Cart();
            user1.setCart(cart);
            cartRepository.save(cart);
            userRepository.save(user1);

            Company company = new Company();
            company.setName("Company " + i);
            company.setAddress("Address " + i);
            company.setPhone("123456789" + i);
            company.setEmail("company" + i + "@example.com");
            company.setLocation("Location " + i);
            companyRepository.save(company);

            // Create 10 items for each company
            for (int j = 0; j < 5; j++) {
                Item item = new Item();
                item.setName("Item " + j + " of Company " + i);
                item.setDescription("Description of Item " + j);
                item.setPrice(10.0 * (j + 1));
                item.setDiscount("10%");
                item.setVolume(100L);
                item.setPictureUrl(FOLDER_PATH+"default.jpg");
                item.setCompany(company);
                itemRepository.save(item);

                // Update the company's items list
                company.getItems().add(item);
            }
            companyRepository.save(company); // Update company with items
        }
    }
}
