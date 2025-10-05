package com.elearning.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "users") // avoid reserved word conflict
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String name;

  @Column(unique = true)
  private String email;

  private String password;

  private String role; // STUDENT or INSTRUCTOR

  // Optional: courses taught by instructor
  @OneToMany(mappedBy = "instructor")
  @JsonIgnore
  private List<Course> coursesTaught;

  // Optional: courses enrolled by student
  @ManyToMany(mappedBy = "students")
  @JsonIgnore
  private List<Course> coursesEnrolled;

  // Constructors
  public User() {}

  public User(String name, String email, String password, String role) {
    this.name = name;
    this.email = email;
    this.password = password;
    this.role = role;
  }

  // Getters and Setters
  public Long getId() { return id; }
  public void setId(Long id) { this.id = id; }

  public String getName() { return name; }
  public void setName(String name) { this.name = name; }

  public String getEmail() { return email; }
  public void setEmail(String email) { this.email = email; }

  public String getPassword() { return password; }
  public void setPassword(String password) { this.password = password; }

  public String getRole() { return role; }
  public void setRole(String role) { this.role = role; }

  public List<Course> getCoursesTaught() { return coursesTaught; }
  public void setCoursesTaught(List<Course> coursesTaught) { this.coursesTaught = coursesTaught; }

  public List<Course> getCoursesEnrolled() { return coursesEnrolled; }
  public void setCoursesEnrolled(List<Course> coursesEnrolled) { this.coursesEnrolled = coursesEnrolled; }
}
