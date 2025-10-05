package com.elearning.controller;

import com.elearning.model.User;
import com.elearning.repository.UserRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
public class AuthController {

  @Autowired
  private UserRepository userRepository;

  // ✅ Login endpoint
  @PostMapping("/login")
  public ResponseEntity<?> login(@RequestBody User loginRequest, HttpSession session) {
    Optional<User> userOpt = userRepository.findByEmail(loginRequest.getEmail());

    if (userOpt.isPresent() && userOpt.get().getPassword().equals(loginRequest.getPassword())) {
      session.setAttribute("user", userOpt.get());
      return ResponseEntity.ok(userOpt.get());
    }
    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
  }

  // ✅ Get current logged-in user
  @GetMapping("/me")
  public ResponseEntity<?> getCurrentUser(HttpSession session) {
    User user = (User) session.getAttribute("user");
    if (user == null) return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    return ResponseEntity.ok(user);
  }

  // ✅ Logout
//  @PostMapping("/logout")
//  public ResponseEntity<?> logout(HttpSession session) {
//    session.invalidate();
//    return ResponseEntity.ok("Logged out");
//  }

  @PostMapping("/logout")
  public ResponseEntity<Map<String, String>> logout(HttpSession session) {
    session.invalidate();
    return ResponseEntity.ok(Map.of("message", "Logged out"));
  }

}
