package com.elearning.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class Course {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String title;

  @Column(length = 2000)
  private String description;

  // Many courses can be taught by one instructor
  @ManyToOne
  @JoinColumn(name = "instructor_id")
  private User instructor;

  // Optional: a course can have multiple enrolled students
  @ManyToMany
  @JoinTable(
    name = "course_student",
    joinColumns = @JoinColumn(name = "course_id"),
    inverseJoinColumns = @JoinColumn(name = "student_id")
  )
  private List<User> students;

  // Optional: course content
  @ElementCollection
  private List<String> lessons;

  // Constructors
  public Course() {}

  public Course(String title, String description, User instructor) {
    this.title = title;
    this.description = description;
    this.instructor = instructor;
  }

  // Getters and Setters
  public Long getId() { return id; }
  public void setId(Long id) { this.id = id; }

  public String getTitle() { return title; }
  public void setTitle(String title) { this.title = title; }

  public String getDescription() { return description; }
  public void setDescription(String description) { this.description = description; }

  public User getInstructor() { return instructor; }
  public void setInstructor(User instructor) { this.instructor = instructor; }

  public List<User> getStudents() { return students; }
  public void setStudents(List<User> students) { this.students = students; }

  public List<String> getLessons() { return lessons; }
  public void setLessons(List<String> lessons) { this.lessons = lessons; }
}
