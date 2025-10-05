package com.elearning.controller;

import com.elearning.model.User;
import com.elearning.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {

  @Autowired
  private UserRepository userRepository;

  // ✅ Create new user
  @PostMapping
  public User createUser(@RequestBody User user) {
    return userRepository.save(user);
  }

  // ✅ Get all users
  @GetMapping
  public List<User> getAllUsers() {
    return userRepository.findAll();
  }

  // ✅ Get user by ID
  @GetMapping("/{id}")
  public ResponseEntity<User> getUserById(@PathVariable Long id) {
    Optional<User> user = userRepository.findById(id);
    return user.map(ResponseEntity::ok)
      .orElseGet(() -> ResponseEntity.notFound().build());
  }

  // ✅ Find user by email
  @GetMapping("/email/{email}")
  public ResponseEntity<User> getUserByEmail(@PathVariable String email) {
    Optional<User> user = userRepository.findByEmail(email);
    return user.map(ResponseEntity::ok)
      .orElseGet(() -> ResponseEntity.notFound().build());
  }
}
