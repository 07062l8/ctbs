package com.example.alibi_endterm.controller;

import com.example.alibi_endterm.dto.UserDTO;
import com.example.alibi_endterm.entity.User;
import com.example.alibi_endterm.security.JwtUtils;
import com.example.alibi_endterm.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/auth")
public class AuthController {
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtils jwtUtil;

    public AuthController(UserService userService, PasswordEncoder passwordEncoder, JwtUtils jwtUtil) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/register")
    public ResponseEntity<User> register(@Valid @RequestBody UserDTO userDTO) {
        User user = new User();
        user.setUsername(userDTO.getUsername());
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));

        return ResponseEntity.ok(userService.register(user));
    }


    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserDTO userDTO) {
        User user = userService.findByUsername(userDTO.getUsername());

        if (user != null && passwordEncoder.matches(userDTO.getPassword(), user.getPassword())) {
            String token = jwtUtil.generateToken(user.getUsername());
            return ResponseEntity.ok(user.getId() + " " + token);
        } else {
            return ResponseEntity.status(401).body("Invalid credentials");
        }
    }
}
