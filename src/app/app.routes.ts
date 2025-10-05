import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './components/home/home.component';
import { LoginComponent } from './components/login/login.component';
import { RegisterComponent } from './components/register/register.component';
import { CoursesComponent } from './components/courses/courses.component';
import { CourseDetailComponent } from './components/course-detail/course-detail.component';

export const routes: Routes = [
  { path: '', component: HomeComponent },                // Home page
  { path: 'login', component: LoginComponent },          // Login page
  { path: 'register', component: RegisterComponent },    // Register page
  { path: 'courses', component: CoursesComponent },      // All courses
  { path: 'course/:id', component: CourseDetailComponent }, // Course detail
  { path: '**', redirectTo: '' }                         // Redirect unknown paths to Home
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
