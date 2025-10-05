import { Component } from '@angular/core';
import { UserService } from '../../services/user/user.service';
import { Router } from '@angular/router';
import { AppModule } from '../../app.module';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css'],
  imports: [AppModule]
})
export class RegisterComponent {

  name: string = '';
  email: string = '';
  password: string = '';
  role: string = 'STUDENT'; // default role
  errorMessage: string = '';
  successMessage: string = '';

  constructor(private userService: UserService, private router: Router) { }

  register(): void {
    const user = {
      name: this.name,
      email: this.email,
      password: this.password,
      role: this.role
    };

    this.userService.createUser(user).subscribe({
      next: (res) => {
        this.successMessage = 'Registration successful!';
        this.errorMessage = '';
        // redirect to login after 2 seconds
        setTimeout(() => this.router.navigate(['/login']), 1000);
      },
      error: (err) => {
        this.errorMessage = 'Registration failed. Try again.';
        this.successMessage = '';
      }
    });
  }
}
