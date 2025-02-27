package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    // ✅ Fetch users within a given ID range (JPQL Query)
    @GetMapping("/range")
    public List<User> getUsersInRange(@RequestParam Long startId, @RequestParam Long endId) {
        return userService.getUsersBetweenIds(startId, endId);
    }

    // ✅ Get all users with sorting & pagination
    @GetMapping
    public List<User> getAllUsers(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String order) {
        Sort.Direction direction = order.equalsIgnoreCase("desc") ? Sort.Direction.DESC : Sort.Direction.ASC;
        Pageable pageable = PageRequest.of(page, size, Sort.by(direction, sortBy));
        return userService.getAllUsers(pageable).getContent();
    }

    // ✅ Get user by ID
    @GetMapping("/{id}")
    public Optional<User> getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    // ✅ Create a new user
    @PostMapping
    public User createUser(@RequestBody @Validated User user) {
        return userService.saveUser(user);
    }

    // ✅ Update user by ID
    @PutMapping("/{id}")
    public Optional<User> updateUser(@PathVariable Long id, @RequestBody @Validated User userDetails) {
        return userService.updateUser(id, userDetails);
    }

    // ✅ Delete user by ID
    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }

    // ✅ Fetch users by name using JPQL
    @GetMapping("/search")
    public List<User> getUsersByName(@RequestParam String name) {
        return userService.getUsersByName(name);
    }
}
