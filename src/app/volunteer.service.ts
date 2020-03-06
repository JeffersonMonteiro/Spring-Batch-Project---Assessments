import { Injectable } from '@angular/core';
import { Volunteer } from './volunteer';
import { VOLUNTEERS } from './mock-volunteers';
import { Observable, of } from 'rxjs';
import { MessageService } from './message.service';


@Injectable({
  providedIn: 'root'
})
export class VolunteerService {

  constructor(private messageService: MessageService) { }

  getVolunteers(): Observable<Volunteer[]> {
    this.messageService.add('VolunteerService: fetched volunteers');
    return of(VOLUNTEERS);
  }

  getVolunteer(id: number): Observable<Volunteer> {
    this.messageService.add(`HeroService: fetched hero id=${id}`);
    return of(VOLUNTEERS.find(volunteer => volunteer.id === id));
  }
}
