package com.elearning.repository;

import com.elearning.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

  // Custom query method: find user by email
  Optional<User> findByEmail(String email);
}
