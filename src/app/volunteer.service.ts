import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs';
import { HttpClientModule, HttpClient, HttpHeaders } from '@angular/common/http';
import { catchError, map, tap } from 'rxjs/operators';

import { Volunteer } from './volunteer';
import { MessageService } from './message.service';

const httpOptions = {
  headers: new HttpHeaders({
    'Content-Type': 'application/json',
    'Authorization': 'my-auth-token'
  })
};

@Injectable({
  providedIn: 'root'
})
export class VolunteerService {

  constructor(private http: HttpClient, private messageService: MessageService) { }

  private log(message: string) {
    this.messageService.add(`VolunteerService: ${message}`);
  }

  private volunteersUrl = 'http://localhost:8080/volunteer';

  getVolunteers(): Observable<Volunteer[]> {
    return this.http.get<Volunteer[]>(this.volunteersUrl + '/get');
  }

  getVolunteer(id: number): Observable<Volunteer> {
    return this.http.get<Volunteer>(this.volunteersUrl + '/get/' + id);
  }

  createVolunteer(volunteer: Volunteer): Observable<Volunteer> {
    return this.http.post<Volunteer>(this.volunteersUrl + '/add', volunteer, httpOptions);
  }

  updateVolunteer(volunteer: Volunteer): Observable<any> {
    return this.http.put(this.volunteersUrl + '/update/' + volunteer.id, volunteer, httpOptions);
  }

  deleteVolunteer(id: number): Observable<Volunteer> {
    return this.http.delete<Volunteer>(this.volunteersUrl + '/delete/' + id);
  }

}
