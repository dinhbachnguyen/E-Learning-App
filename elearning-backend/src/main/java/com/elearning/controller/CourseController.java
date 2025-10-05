package com.elearning.controller;

import com.elearning.dto.CourseDto;
import com.elearning.model.Course;
import com.elearning.model.User;
import com.elearning.repository.CourseRepository;
import com.elearning.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/courses")
public class CourseController {

  @Autowired
  private CourseRepository courseRepository;

  @Autowired
  private UserRepository userRepository;

  // ✅ Create a new course with instructor
  @PostMapping
  public ResponseEntity<Course> createCourse(@RequestParam Long instructorId, @RequestBody Course course) {
    Optional<User> instructor = userRepository.findById(instructorId);
    if (instructor.isEmpty() || !"INSTRUCTOR".equalsIgnoreCase(instructor.get().getRole())) {
      return ResponseEntity.badRequest().build();
    }
    course.setInstructor(instructor.get());
    Course savedCourse = courseRepository.save(course);
    return ResponseEntity.ok(savedCourse);
  }

//  @PostMapping
//  public ResponseEntity<Course> createCourse(@RequestBody CourseDto dto) {
//    Optional<User> instructorOpt = userRepository.findById(dto.getInstructorId());
//    if (instructorOpt.isEmpty() || !"INSTRUCTOR".equalsIgnoreCase(instructorOpt.get().getRole())) {
//      return ResponseEntity.badRequest().build();
//    }
//
//    User instructor = instructorOpt.get();
//    Course course = new Course(dto.getTitle(), dto.getDescription(), instructor);
//    Course savedCourse = courseRepository.save(course);
//    return ResponseEntity.ok(savedCourse);
//  }


  // ✅ Get all courses
  @GetMapping
  public List<Course> getAllCourses() {
    return courseRepository.findAll();
  }

  // ✅ Get a course by ID
  @GetMapping("/{id}")
  public ResponseEntity<Course> getCourseById(@PathVariable Long id) {
    Optional<Course> course = courseRepository.findById(id);
    return course.map(ResponseEntity::ok)
      .orElseGet(() -> ResponseEntity.notFound().build());
  }

  // ✅ Enroll a student into a course
  @PostMapping("/{courseId}/enroll/{studentId}")
  public ResponseEntity<Course> enrollStudent(@PathVariable Long courseId, @PathVariable Long studentId) {
    Optional<Course> courseOpt = courseRepository.findById(courseId);
    Optional<User> studentOpt = userRepository.findById(studentId);

    if (courseOpt.isPresent() && studentOpt.isPresent()) {
      User student = studentOpt.get();
      if (!"STUDENT".equalsIgnoreCase(student.getRole())) {
        return ResponseEntity.badRequest().build();
      }

      Course course = courseOpt.get();

      if (course.getStudents().contains(student)) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(course);
      }

      course.getStudents().add(student);
      courseRepository.save(course);
      return ResponseEntity.ok(course);
    }
    return ResponseEntity.notFound().build();
  }
}
