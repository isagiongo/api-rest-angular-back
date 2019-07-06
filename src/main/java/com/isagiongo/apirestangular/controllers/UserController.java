package com.isagiongo.apirestangular.controllers;

import com.isagiongo.apirestangular.models.User;
import com.isagiongo.apirestangular.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/users")
    public List<User> getUser(){
        return (List<User>) userRepository.findAll();
    }

    @PostMapping("/users")
    public ResponseEntity<User> addUser(@RequestBody User user) {
        User userSave = userRepository.save(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(userSave);
    }
}
