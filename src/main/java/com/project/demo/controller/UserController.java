package com.project.demo.controller;

import com.project.demo.model.User;
import com.project.demo.model.UserRegisterRequest;
import com.project.demo.repository.UserRepository;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @SecurityRequirement(name = "protected")
    @GetMapping("/home")
    public String home(Authentication authentication) {
        return "Hello " + authentication.getName();
    }

    @PostMapping("/register")
    public String register(@RequestBody UserRegisterRequest userRegisterRequest) {
        Optional<User> existingUser = userRepository.findByUsername(userRegisterRequest.getUsername());
        if (existingUser.isPresent()) {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "Username already exists");
        }
        User newUser = User.builder()
                .username(userRegisterRequest.getUsername())
                .password(passwordEncoder.encode(userRegisterRequest.getPassword()))
                .build();
        userRepository.save(newUser);
        return "User created";
    }

}
