package com.example.groceriesShoping.controller;

import com.example.groceriesShoping.dto.UserDto;
import com.example.groceriesShoping.model.User;
import com.example.groceriesShoping.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("api/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/list")
    public List<User> getAllUsers(){
        return userService.findAll();
    }
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id){
        User user=userService.findById(id);
        if(user!=null){
            return ResponseEntity.ok(user);
        }else{
            return ResponseEntity.notFound().build();
        }
    }
    @PostMapping("/add")
    public ResponseEntity<User> addUser(@RequestBody UserDto user){
        User Us=userService.addUser(user);
        if(Us!=null){
            return ResponseEntity.ok(Us);
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<User> editUser(@PathVariable Long id, @RequestBody UserDto user){
        User user1=userService.updateUser(id,user);
        if(user1!=null){
            return ResponseEntity.ok(user1);
        }
        return ResponseEntity.notFound().build();
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<User> deleteUser(@PathVariable Long id) {
        User us=userService.findById(id);
        if(us!=null){
            userService.deleteById(id);
            return ResponseEntity.ok(us);
        }else {
            return ResponseEntity.notFound().build();
        }
    }
}
