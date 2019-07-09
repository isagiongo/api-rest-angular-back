package com.isagiongo.apirestangular.services;

import com.isagiongo.apirestangular.models.User;
import com.isagiongo.apirestangular.models.dtos.UserDTO;
import com.isagiongo.apirestangular.repositories.UserRepository;
import com.isagiongo.apirestangular.services.exceptions.UserNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> findAll() {
        return (List<User>) userRepository.findAll();
    }

    public User findById(Long id) {
        Optional<User> user = userRepository.findById(id);
        return user.orElseThrow(() -> new UserNotFoundException("User not found! Id: " + id));
    }

    public User save(User user){
        return userRepository.save(user);
    }

    public void deleteById(Long id) {
        findById(id);
        userRepository.deleteById(id);
    }

    public User fromDto(UserDTO userDTO) {
        return new User(userDTO.getName(), userDTO.getEmail());
    }
}
