package com.example.groceriesShoping.controller.auth;

import com.example.groceriesShoping.dto.UserDto;
import com.example.groceriesShoping.repository.UserRepository;
import com.example.groceriesShoping.service.AuthService;
import com.example.groceriesShoping.service.JwtService;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:3000")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final JwtService jwtService;
    private final UserRepository userRepository;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody UserDto userDto) {
        return ResponseEntity.ok(authService.register(userDto));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody AuthenticationRequest request) {
        return ResponseEntity.ok(authService.authenticate(request));

    }
    @PostMapping("/refresh-token")
    public ResponseEntity<?> refreshToken(HttpServletRequest request) {
        String refreshToken = request.getHeader("Authorization").replace("Bearer ", "");
        try {

            String username = jwtService.extractUserEmail(refreshToken);
            UserDetails userDetails = userRepository.findByEmail(username).orElseThrow();
            if (jwtService.isTokenValid(refreshToken, userDetails)) {
                String newToken = jwtService.generateToken(userDetails);
                String newRefreshToken = jwtService.generateRefreshToken(userDetails);
                Map<String, String> tokens = new HashMap<>();
                tokens.put("token", newToken);
                tokens.put("refreshToken", newRefreshToken);
                return ResponseEntity.ok(tokens);
            }
        } catch (ExpiredJwtException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Refresh token expired");
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid refresh token");
    }


}
