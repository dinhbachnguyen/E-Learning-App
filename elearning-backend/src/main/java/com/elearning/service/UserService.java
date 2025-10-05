package com.elearning.service;

import com.elearning.model.User;
import com.elearning.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

  @Autowired
  private UserRepository userRepository;

  // ✅ Create or update user
  public User saveUser(User user) {
    return userRepository.save(user);
  }

  // ✅ Get all users
  public List<User> getAllUsers() {
    return userRepository.findAll();
  }

  // ✅ Get user by ID
  public Optional<User> getUserById(Long id) {
    return userRepository.findById(id);
  }

  // ✅ Find user by email
  public Optional<User> getUserByEmail(String email) {
    return userRepository.findByEmail(email);
  }

  // ✅ Delete user
  public void deleteUser(Long id) {
    userRepository.deleteById(id);
  }
}
