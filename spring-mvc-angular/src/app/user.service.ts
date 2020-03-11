import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpErrorResponse } from '@angular/common/http';
import { catchError, map, tap } from 'rxjs/operators';
import { Observable, of } from 'rxjs';

import { User } from './user';
import "@angular/compiler";
import { Product } from './product';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json'})
}; 

@Injectable({ providedIn: 'root' })
export class UserService {

  constructor(private http: HttpClient) { }
  private usersUrl = 'http://localhost:8080/User';

  getUsers(): Observable<User[]> {
    const url = `${this.usersUrl}/get`;
    return this.http.get<User[]>(url);
  }

  getUser(id: number): Observable<User> {
    const url = `${this.usersUrl}/get/${id}`;
    return this.http.get<User>(url);
  }

  addUser (user: User): Observable<User> {
    const url = `${this.usersUrl}/add`;
    return this.http.post<User>(url, user, httpOptions).pipe();
  }
 
  addProduct(product: Product, idUser: number): Observable<Product> {
    const url = `${this.usersUrl}/${idUser}/Product/add`;
    return this.http.post<Product>(url, product, httpOptions).pipe();
 }

  deleteUser (user: User | number): Observable<User> {
    const id = typeof user === 'number' ? user : user.idUser;
    const url = `${this.usersUrl}/delete/${id}`;
    return this.http.delete<User>(url, httpOptions);
  }

  updateUser (user: User): Observable<any> {
    const id = typeof user === 'number' ? user : user.idUser;
    const url = `${this.usersUrl}/${id}`;
    return this.http.put(url, user, httpOptions);
  }

  searchUsers(term: string): Observable<User[]> {
    if (!term.trim()) {
      return of([]);
    }
    return this.http.get<User[]>(`${this.usersUrl}/name/${term}`).pipe(
      tap(x => x.length ?
        console.log(`found usres matching "${term}"`) :
        console.log(`no users matching "${term}"`)),
      catchError(this.handleError<User[]>('searchHeroes', []))
    );
  }
 
  private handleError<T> (operation = 'operation', result?: T) {
    return (error: any): Observable<T> => {
      console.error(error);
      return of(result as T);
    };
  }

  deleteProduct (product: Product | number, idUser: number): Observable<Product> {
    const id = typeof product === 'number' ? product : product.idProduct;
    const url = `${this.usersUrl}/${idUser}/Product/delete/${id}`;
    return this.http.delete<Product>(url, httpOptions);
  }
}
