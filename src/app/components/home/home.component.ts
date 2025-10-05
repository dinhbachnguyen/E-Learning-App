import { Component } from '@angular/core';
import { AuthService } from '../../services/auth/auth.service';
import { AppModule } from '../../app.module';

@Component({
  selector: 'app-home.component',
  imports: [AppModule],
  templateUrl: './home.component.html',
  styleUrl: './home.component.css'
})
export class HomeComponent {
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
}
