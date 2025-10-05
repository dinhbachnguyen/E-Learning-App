import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { CourseService } from '../../services/course/course.service';
import { AppModule } from '../../app.module';

@Component({
  selector: 'app-course-detail',
  templateUrl: './course-detail.component.html',
  styleUrls: ['./course-detail.component.css'],
  imports: [AppModule]
})
export class CourseDetailComponent implements OnInit {

  course: any;
  currentUser: any;
  message: string = '';

  constructor(
    private route: ActivatedRoute,
    private courseService: CourseService
  ) { }

  ngOnInit(): void {
    this.currentUser = JSON.parse(localStorage.getItem('currentUser') || '{}');
    const courseId = Number(this.route.snapshot.paramMap.get('id'));
    this.loadCourse(courseId);
  }

  loadCourse(id: number): void {
    this.courseService.getCourseById(id).subscribe({
      next: (data) => {
        this.course = data;
      },
      error: () => {
        this.message = 'Failed to load course details.';
      }
    });
  }

  enroll(): void {
    if (!this.currentUser || this.currentUser.role !== 'STUDENT') {
      alert('Only students can enroll.');
      return;
    }

    this.courseService.enrollStudent(this.course.id, this.currentUser.id).subscribe({
      next: () => {
        this.message = 'Enrolled successfully!';
        this.loadCourse(this.course.id);
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
}
