package com.elearning.service;

import com.elearning.model.Course;
import com.elearning.model.User;
import com.elearning.repository.CourseRepository;
import com.elearning.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CourseService {

  @Autowired
  private CourseRepository courseRepository;

  @Autowired
  private UserRepository userRepository;

  // ✅ Create course with instructor
  public Optional<Course> createCourse(Long instructorId, Course course) {
    Optional<User> instructor = userRepository.findById(instructorId);
    if (instructor.isPresent() && "INSTRUCTOR".equalsIgnoreCase(instructor.get().getRole())) {
      course.setInstructor(instructor.get());
      return Optional.of(courseRepository.save(course));
    }
    return Optional.empty();
  }

  // ✅ Get all courses
  public List<Course> getAllCourses() {
    return courseRepository.findAll();
  }

  // ✅ Get course by ID
  public Optional<Course> getCourseById(Long id) {
    return courseRepository.findById(id);
  }

  // ✅ Enroll student in course
  public Optional<Course> enrollStudent(Long courseId, Long studentId) {
    Optional<Course> courseOpt = courseRepository.findById(courseId);
    Optional<User> studentOpt = userRepository.findById(studentId);

    if (courseOpt.isPresent() && studentOpt.isPresent()) {
      User student = studentOpt.get();
      if (!"STUDENT".equalsIgnoreCase(student.getRole())) {
        return Optional.empty(); // not a student
      }

      Course course = courseOpt.get();
      course.getStudents().add(student);
      return Optional.of(courseRepository.save(course));
    }
    return Optional.empty();
  }
}
