import { Component, OnInit } from '@angular/core';
import { CourseService } from '../../services/course/course.service';
import { AppModule } from '../../app.module';


@Component({
  selector: 'app-courses',
  templateUrl: './courses.component.html',
  styleUrls: ['./courses.component.css'],
  imports: [AppModule]
})
export class CoursesComponent implements OnInit {

  courses: any[] = [];
  currentUser: any;

  // For instructors: create course
  newCourse = { title: '', description: '' };
  message: string = '';
  course: any;

  constructor(private courseService: CourseService) { }

  ngOnInit(): void {
    this.currentUser = JSON.parse(localStorage.getItem('currentUser') || '{}');
    console.log("current user = ", this.currentUser)
    this.loadCourses();
  }

  logCourseId(id: number) {
    // console.log('Course ID clicked:', id);
  }

  loadCourses(): void {
    this.courseService.getCourses().subscribe(data => {
      this.courses = data;
    });
  }

  enroll(courseId: number): void {
    if (!this.currentUser || this.currentUser.role !== 'STUDENT') {
      alert('Only students can enroll.');
      return;
    }

    this.courseService.enrollStudent(courseId, this.currentUser.id).subscribe({
      next: () => {
        this.message = 'Enrolled successfully!';
        this.loadCourses();
      },
      error: (err) => {
        if (err.status === 409) {
          this.message = 'You are already enrolled in this course.'; 
        } else {
          this.message = 'Enrollment failed.';
        }
      }
    });
  }

  createCourse(): void {
    if (!this.currentUser || this.currentUser.role !== 'INSTRUCTOR') {
      alert('Only instructors can create courses.');
      return;
    }

    this.courseService.createCourse(this.currentUser.id, this.newCourse).subscribe({
      next: () => {
        this.message = 'Course created successfully!';
        this.newCourse = { title: '', description: '' };
        this.loadCourses();
      },
      error: () => {
        this.message = 'Course creation failed.';
      }
    });
  }
}
