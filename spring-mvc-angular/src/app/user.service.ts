import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';

import { Observable, of } from 'rxjs';

import { User } from './user';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({ providedIn: 'root' })
export class UserService {

  constructor(private http: HttpClient) { }

  private usersUrl = 'localhost:8080/User';  // URL to web api

  getUsers(): Observable<User[]> {
    const url = `${this.usersUrl}/get`;
    return this.http.get<User[]>(this.usersUrl)
  }

  getUser(id: number): Observable<User> {
    const url = `${this.usersUrl}/get/${id}`;
    return this.http.get<User>(url);
  }

  addUser (user: User): Observable<User> {
    const url = `${this.usersUrl}/add`;
    return this.http.post<User>(url, user, httpOptions);
  }
 
  /** DELETE: delete the hero from the server */
  deleteUser (user: User | number): Observable<User> {
    const id = typeof user === 'number' ? user : user.userId;
    const url = `${this.usersUrl}/delete/${id}`;
    return this.http.delete<User>(url, httpOptions);
  }

  updateUser (user: User): Observable<any> {
    return this.http.put(this.usersUrl, user, httpOptions);
  }

}
