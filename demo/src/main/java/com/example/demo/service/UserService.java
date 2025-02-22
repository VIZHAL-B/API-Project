package com.example.demo.service;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // Fetch all users with optional sorting
    public List<User> getAllUsers(Sort sort) {
        return userRepository.findAll(sort);
    }

    // Fetch a user by ID
    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    // Save a new user
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    // Update an existing user
    public Optional<User> updateUser(Long id, User userDetails) {
        return userRepository.findById(id).map(existingUser -> {
            existingUser.setName(userDetails.getName());
            existingUser.setEmail(userDetails.getEmail());
            existingUser.setPassword(userDetails.getPassword());
            existingUser.setPreferredLanguage(userDetails.getPreferredLanguage());
            existingUser.setProfilePicture(userDetails.getProfilePicture());
            return userRepository.save(existingUser);
        });
    }

    // Delete a user by ID
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    // Fetch users by email (Custom JPA method)
    public Optional<User> getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    // Fetch users by preferred language (Custom JPA method with sorting)
    public List<User> getUsersByPreferredLanguage(String language, Sort sort) {
        return userRepository.findByPreferredLanguage(language, sort);
    }
    public Page<User> getAllUsers(Pageable pageable) {
        return userRepository.findAll(pageable);
    }
    
}
