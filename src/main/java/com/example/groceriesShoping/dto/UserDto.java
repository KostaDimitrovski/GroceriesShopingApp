package com.example.groceriesShoping.dto;

import com.example.groceriesShoping.enums.Role;
import lombok.Data;

@Data
public class UserDto {
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String address;
    private Role role;
    private String password;
}
