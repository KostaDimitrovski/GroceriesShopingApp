package com.example.groceriesShoping.service.impl;

import com.example.groceriesShoping.dto.UserDto;
import com.example.groceriesShoping.enums.Role;
import com.example.groceriesShoping.model.User;
import com.example.groceriesShoping.repository.UserRepository;
import com.example.groceriesShoping.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User findById(Long id) {
        // TODO: napravi exceptions
        return userRepository.findById(id).orElseThrow();
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public User addUser(UserDto user) {
        User user1=new User();
        user1.setFirstName(user.getFirstName());
        user1.setLastName(user.getLastName());
        user1.setEmail(user.getEmail());
        user1.setRole(user.getRole());
        user1.setPassword(user.getPassword());
        userRepository.save(user1);
        return user1;
    }

    @Override
    public User updateUser(Long id, UserDto user) {
        User user1=userRepository.findById(id).orElseThrow();
        user1.setFirstName(user.getFirstName());
        user1.setLastName(user.getLastName());
        user1.setEmail(user.getEmail());
        user1.setRole(user.getRole());
        user1.setPassword(user.getPassword());
        userRepository.save(user1);
        return user1;
    }

    @Override
    public void deleteById(Long id) {
        User user= userRepository.findById(id).orElseThrow();
        userRepository.delete(user);
    }

    @Override
    public User register(String password, String repeatPassword, String name, String surname, Role role) {
        // TODO:: register napravi
        return null;
    }
}
