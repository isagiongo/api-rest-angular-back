package com.isagiongo.apirestangular.controllers;

import com.isagiongo.apirestangular.models.User;
import com.isagiongo.apirestangular.repositories.UserRepository;
import com.isagiongo.apirestangular.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<User>> getUsers(){
        List<User> users = (List<User>) userService.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(users);
    }

    @GetMapping(value="/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        return userService.findById(id)
                .map(user -> ResponseEntity.status(HttpStatus.OK).body(user))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PostMapping
    public ResponseEntity<User> addUser(@RequestBody User user) {
        User userSave = userService.save(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(userSave);
    }
}
