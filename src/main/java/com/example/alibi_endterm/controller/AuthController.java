package com.example.alibi_endterm.controller;

import com.example.alibi_endterm.dto.UserDTO;
import com.example.alibi_endterm.entity.User;
import com.example.alibi_endterm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody UserDTO userDTO) {
        User user = new User();
        user.setUsername(userDTO.getUsername());
        user.setPassword(userDTO.getPassword());
        userService.register(user);
        return ResponseEntity.ok("User registered successfully");
    }

    @RestController
    public class RootController {
        @GetMapping("/")
        public String home() {
            return "Welcome to the root page!";
        }
    }

}
