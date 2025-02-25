package com.example.demo.service;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // ✅ Fetch users within a given ID range (JPQL Query)
    public List<User> getUsersBetweenIds(Long startId, Long endId) {
        return userRepository.findUsersInRange(startId, endId);
    }

    // ✅ Get all users with sorting & pagination
    public Page<User> getAllUsers(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    // ✅ Get user by ID
    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    // ✅ Create a new user
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    // ✅ Update user by ID
    public Optional<User> updateUser(Long id, User userDetails) {
        return userRepository.findById(id).map(user -> {
            user.setName(userDetails.getName());
            user.setEmail(userDetails.getEmail());
            return userRepository.save(user);
        });
    }

    // ✅ Delete user by ID
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    // ✅ Fetch users by name using JPQL
    public List<User> getUsersByName(String name) {
        return userRepository.findUsersByName(name);
    }
}
