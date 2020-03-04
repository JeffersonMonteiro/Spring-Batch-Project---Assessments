import { Customer } from './customer';
import { Observable } from 'rxjs';
import { Injectable } from '@angular/core';
import { HttpClient, HttpClientModule, HttpHeaders,  } from '@angular/common/http';

const httpOptions = {
  headers: new HttpHeaders({
    'Content-Type':  'application/json',
    'Authorization': 'my-auth-token'
  })
};

@Injectable({
  providedIn: 'root'
})
export class HttpService {

  private baseUrl = 'http://localhost:8080/customer';

  constructor(private http: HttpClient) { }

  getCustomers(): Observable<Customer[]>{
    return this.http.get<Customer[]>(this.baseUrl).pipe();
  }

  createCustomer(customer: Object): Observable<Object> {
    return this.http.post(this.baseUrl, customer, httpOptions);
  }

  updateCustomer(customerId: number, value: any): Observable<Object> {
    return this.http.put(`${this.baseUrl}/${customerId}`, value);
  }

  deleteCustomer(customerId: number): Observable<any> {
    return this.http.delete(`${this.baseUrl}/${customerId}`, { responseType: 'text' });
  }


}
