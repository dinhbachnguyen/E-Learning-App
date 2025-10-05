package com.elearning;

import com.elearning.repository.UserRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.lang.NonNull;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.boot.CommandLineRunner;
import com.elearning.repository.CourseRepository;
import com.elearning.model.Course;
import com.elearning.model.User;


@SpringBootApplication
public class ElearningBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(ElearningBackendApplication.class, args);
	}

  @Bean
  public WebMvcConfigurer corsConfigurer() {
    return new WebMvcConfigurer() {
      @Override
      public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
          .allowedOrigins("http://localhost:4200")
          .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
          .allowCredentials(true);
      }
    };
  }


  @Bean
  public CommandLineRunner loadSampleCourses(CourseRepository repo, UserRepository user) {
    return args -> {

      User hannah = new User("Hannah", "hannah@example.com", "pass123", "INSTRUCTOR");
      user.save(hannah);
      User james = new User("James", "james@example.com", "pass123", "INSTRUCTOR");
      user.save(james);

      repo.save(new Course("Java Basics", "Learn the fundamentals of Java", james));
      repo.save(new Course("Spring Boot", "Build REST APIs with Spring Boot",  hannah));
      repo.save(new Course("Angular", "Frontend development with Angular", james));
    };
  }
}
