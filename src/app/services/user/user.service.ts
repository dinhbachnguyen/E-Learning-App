import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  private apiUrl = 'http://localhost:8080/api/users';

  constructor(private http: HttpClient) { }

  // Register a new user
  createUser(user: any): Observable<any> {
    return this.http.post<any>(this.apiUrl, user);
  }

  // Get all users
  getUsers(): Observable<any[]> {
    return this.http.get<any[]>(this.apiUrl);
  }

  // Get user by ID
  getUserById(id: number): Observable<any> {
    return this.http.get<any>(`${this.apiUrl}/${id}`);
  }

  // Get user by email (for login)
  getUserByEmail(email: string): Observable<any> {
    return this.http.get<any>(`${this.apiUrl}/email/${email}`);
  }

  // Future: Update user info
  // updateUser(id: number, user: any): Observable<any> {
  //   return this.http.put<any>(`${this.apiUrl}/${id}`, user);
  // }
}
