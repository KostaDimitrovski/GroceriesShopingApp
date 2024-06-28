package com.example.groceriesShoping.service;


import com.example.groceriesShoping.dto.UserDto;
import com.example.groceriesShoping.enums.Role;
import com.example.groceriesShoping.model.User;
import com.example.groceriesShoping.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;


public interface UserService  {
    List<User> findAll();
    User findById(Long id);
    User findByEmail(String email);
    User findByUsername(String username);
    User addUser(UserDto user);
    User updateUser(Long id,UserDto user);
    void deleteById(Long id);
    User register( String password, String repeatPassword, String name, String surname, Role role);

}
