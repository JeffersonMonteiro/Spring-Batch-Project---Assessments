import { Injectable } from '@angular/core';
import { Volunteer } from './volunteer';
import { VOLUNTEERS } from './mock-volunteers';
import { Observable, of } from 'rxjs';
import { MessageService } from './message.service';


@Injectable({
  providedIn: 'root'
})
export class VolunteerService {

  constructor() { }

  getVolunteers(): Observable<Volunteer[]> {
    return of(VOLUNTEERS);
  }

}
