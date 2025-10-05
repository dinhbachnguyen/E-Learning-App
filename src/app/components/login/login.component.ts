import { Component } from '@angular/core';
import { UserService } from '../../services/user/user.service';
import { Router } from '@angular/router';
import { AppModule } from '../../app.module';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
  imports: [AppModule]

})
export class LoginComponent {

  email: string = '';
  password: string = '';
  message: string = '';
  errorMessage: string = '';

  constructor(private userService: UserService, private router: Router) { }

  login(): void {
    this.message = ""
    this.errorMessage = ""
    // For now, we'll call backend to fetch user by email
    this.userService.getUserByEmail(this.email).subscribe({
      next: (user) => {
        if (user && user.password === this.password) {
          // Save user info in localStorage or sessionStorage
          localStorage.setItem('currentUser', JSON.stringify(user));
          // this.router.navigate(['/courses']);
          console.log("login success")
          setTimeout(() => {
            window.location.href = '/';
          }, 1000);
          // window.location.reload();
          this.message = 'Login successfully';
        } else {
          this.errorMessage = 'Invalid email or password';
        }
      },
      error: () => {
        this.errorMessage = 'User not found';
      }
    });
  }
}
