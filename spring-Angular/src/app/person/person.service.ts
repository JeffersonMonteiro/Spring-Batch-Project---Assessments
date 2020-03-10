import { HttpClient, HttpErrorResponse, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Person } from './person';
import { catchError, map } from 'rxjs/operators';
import "@angular/compiler";
import { Product } from './product';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable()
export class PersonService {

  constructor(private _httpService: HttpClient) { }

  getAllPerson(): Observable<Person[]> {
    return this._httpService.get<Person[]>("http://localhost:8080/person")
  }

  private handleError(error: Response) {
    return Observable.throw(error);
  }

  addPerson(person: Person) {

    if (person.id) {
      return this._httpService.put("http://localhost:8080/person/" + person.id, person, httpOptions)
    }
    else {
      return this._httpService.post<Person>("http://localhost:8080/person", person, httpOptions)
    }
  }

  deletePerson(personId: string) {
    return this._httpService.delete("http://localhost:8080/person/" + personId);
  }

  updatePerson(personId: string): Observable<any> {
    return this._httpService.get("http://localhost:8080/person/" + personId);
  }

  //---------------------------------------------------------------------------------//

  /*addProduct(product: Product, person: Person) {

    if (product.id) {
      return this._httpService.put("http://localhost:8080/person/" + person.id + "/product/" + product.id, product, httpOptions)
    }
    else {
      return this._httpService.post<Product>("http://localhost:8080/person/" + person.id + "/product/", product, httpOptions)
    }
  }

  updateProduct(personId: string): Observable<any> {
    return this._httpService.get("http://localhost:8080/person/" + personId);
  }*/

}
