package com.example.groceriesShoping.service;

import com.example.groceriesShoping.controller.auth.AuthenticationRequest;
import com.example.groceriesShoping.controller.auth.AuthenticationResponse;
import com.example.groceriesShoping.controller.auth.RegisterRequest;
import com.example.groceriesShoping.dto.UserDto;

public interface AuthService {
    AuthenticationResponse authenticate(AuthenticationRequest request);

    AuthenticationResponse register(UserDto userDto);
    AuthenticationResponse refresh(String refreshToken);

}
