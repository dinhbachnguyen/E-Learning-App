import { Component, OnInit } from '@angular/core';
import { RouterModule } from '@angular/router';
import { AppModule } from '../../app.module';
import { AuthService } from '../../services/auth/auth.service';


@Component({
  selector: 'app-navbar',
  imports: [RouterModule, AppModule],
  templateUrl: './navbar.component.html',
  styleUrl: './navbar.component.css'
})
export class NavbarComponent implements OnInit {
  currentUser: any = null;

  constructor(private authService: AuthService) { }

  ngOnInit(): void {
    this.authService.currentUser$.subscribe(user => {
      this.currentUser = user;
      console.log("cur=", this.currentUser)
    });
    // const userData = localStorage.getItem('currentUser');
    // if (userData) {
    //   this.currentUser = JSON.parse(userData);
    //   console.log("cur user =", this.currentUser)
    // }
  }

  logout() {
    this.authService.logout().subscribe(() => {
      this.currentUser = null;
      window.location.href = '/login';
    });
  }
}
