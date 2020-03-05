import { Component, OnInit } from '@angular/core';

import { Volunteer } from '../volunteer';
import { VOLUNTEERS } from '../mock-volunteers'
import {VolunteerService} from '../volunteer.service'
import { MessageService } from '../message.service';

@Component({
  selector: 'app-volunteers',
  templateUrl: './volunteers.component.html',
  styleUrls: ['./volunteers.component.css', '../app.component.css']
})
export class VolunteersComponent implements OnInit {

  volunteers: Volunteer[];
  selectedVolunteer: Volunteer;

  onSelect(volunteer: Volunteer): void {
    this.selectedVolunteer = volunteer;
    this.messageService.add(`VolunteerService: Selected name=${volunteer.name}`);
  }

  constructor(private volunteerService: VolunteerService, private messageService: MessageService) { }

  ngOnInit(): void {
    this.getVolunteers();
  }

  getVolunteers(): void {
    this.volunteerService
      .getVolunteers()
      .subscribe(volunteers => this.volunteers = volunteers);
  }

}
