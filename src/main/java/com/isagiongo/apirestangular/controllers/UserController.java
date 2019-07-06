package com.isagiongo.apirestangular.controllers;

import com.isagiongo.apirestangular.models.User;
import com.isagiongo.apirestangular.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/users")
    public Iterable<User> getUser(){
        return userRepository.findAll();
    }

    @PostMapping("/users")
    public void addUser(@RequestBody User user) {
        userRepository.save(user);
    }
}
