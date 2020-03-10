import { Injectable } from '@angular/core';
import { Person } from './person';
import { Observable, of } from 'rxjs';
import { MessageService } from './message.service';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { catchError, map, tap } from 'rxjs/operators';
import { Product } from './product';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({
  providedIn: 'root'
})


export class PersonService { 

  private peopleUrl = 'http://localhost:8080/person';

  constructor(private http: HttpClient,
    private messageService: MessageService) { }

  getPeople(): Observable<Person[]> {
    //this.messageService.add('PersonService: fetched people');
    return this.http.get<Person[]>(this.peopleUrl);
  }

  getPerson(id: number): Observable<Person> {
    //this.messageService.add('PersonService: fetched person id=${id}');
    const url = `${this.peopleUrl}/${id}`;
    return this.http.get<Person>(url);
  }

  updatePerson(person: Person | number): Observable<any> {
    const id = typeof person === 'number' ? person : person.id;
    const url = `${this.peopleUrl}/${id}`;
    return this.http.put(url, person, httpOptions);
  }

  addPerson(person: Person): Observable<Person> {
    return this.http.post<Person>(this.peopleUrl, person, httpOptions);
  }

  deletePerson(person: Person | number): Observable<Person> {
    const id = typeof person === 'number' ? person : person.id;
    const url = `${this.peopleUrl}/${id}`;
    return this.http.delete<Person>(url, httpOptions);
  }

  /* GET heroes whose name contains search term */
  searchPeople(term: string): Observable<Person[]> {
    if (!term.trim()) {
      // if not search term, return empty hero array.
      return of([]);
    }
    return this.http.get<Person[]>(`${this.peopleUrl}/name/${term}`);
  }


  
  addProduct(product: Product , personId: number): Observable<Product> {
    const url = `${this.peopleUrl}/${personId}/product/`;
    return this.http.post<Product>(url, product, httpOptions);
  }
  
  deleteProduct(personId: number, product: Product | number ): Observable<Product> {
    const productId = typeof product === 'number' ? product : product.id;
    const url = `${this.peopleUrl}/${personId}/product/${productId}`;
    return this.http.delete<Product>(url, httpOptions);
  }
}
