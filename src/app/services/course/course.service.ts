import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class CourseService {

  private apiUrl = 'http://localhost:8080/api/courses';

  constructor(private http: HttpClient) { }

  // Get all courses
  getCourses(): Observable<any[]> {
    return this.http.get<any[]>(this.apiUrl);
  }

  // Get course by ID
  getCourseById(courseId: number): Observable<any> {
    return this.http.get<any>(`${this.apiUrl}/${courseId}`);
  }

  // Enroll student in course
  enrollStudent(courseId: number, studentId: number): Observable<any> {
    return this.http.post<any>(`${this.apiUrl}/${courseId}/enroll/${studentId}`, {});
  }

  // Create course (for instructors)
  createCourse(instructorId: number, course: any): Observable<any> {
    return this.http.post<any>(`${this.apiUrl}?instructorId=${instructorId}`, course);
  }

  // Future: add lessons to course
  // addLesson(courseId: number, lesson: any): Observable<any> {
  //   return this.http.post<any>(`${this.apiUrl}/${courseId}/lessons`, lesson);
  // }
}
