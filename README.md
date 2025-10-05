
# E-Learning Platform

**E-Learning Platform** is a full-stack web application designed for online learning. It allows instructors to create and manage courses while students can browse, enroll, and track their learning progress. Built with **Angular** for the frontend and **Spring Boot** for the backend, this app provides a responsive, interactive experience.

---

## Features

- **User Authentication**: Secure login and registration for students and instructors.  
- **Instructor Dashboard**: Create, edit, and manage courses.  
- **Student Dashboard**: Browse available courses and enroll.  
- **Course Enrollment**: Prevent duplicate enrollments and manage enrolled students.  
- **Real-time Updates**: Navbar and course information automatically update on login/logout.  
- **Responsive UI**: Mobile-friendly design using Bootstrap.  
- **Backend**: Spring Boot REST API with H2 in-memory database (or other relational DB).  
- **CORS Support**: Smooth interaction between frontend and backend during development.

---

## Tech Stack

- **Frontend**: Angular, TypeScript, Bootstrap  
- **Backend**: Java, Spring Boot, Spring Data JPA, H2 Database  
- **API**: RESTful endpoints for users, courses, and enrollments  
- **Build Tools**: Maven for backend, Angular CLI for frontend  

---

## Project Structure

```

project-root/
├─ elearning-project/        ← Angular application
  └─ elearning-backend/      ← Spring Boot application

```

---

## Getting Started

### Prerequisites

- Java 17+  
- Node.js 16+  
- Angular CLI 16+  
- Maven

### Installation

1. Clone the repository:  
   ```bash
   git clone https://github.com/dinhbachnguyen/E-Learning-App.git
   cd elearning-project
```

2. **Backend**:

   ```bash
   cd elearning-backend
   mvn spring-boot:run   
   ```

3. **Frontend**:

   ```bash
   cd ../elearning-project
   npm install
   ng serve
   ```

4. Open your browser at [http://localhost:4200](http://localhost:4200)

---

## Usage

* Register as a **Student** or **Instructor**.
* Instructors can create new courses and manage existing ones.
* Students can enroll in courses and view enrolled students.
* UI dynamically updates to show the current user and logout option.
* Duplicate enrollments are prevented and students are notified if they are already enrolled.

---

## Notes

* The backend uses an **H2 in-memory database** by default; data is reset on restart.
* For production, configure a persistent database like MySQL or PostgreSQL.
