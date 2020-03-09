import { Component, OnInit } from '@angular/core';

import { Volunteer } from '../volunteer';
import {VolunteerService} from '../volunteer.service'

@Component({
  selector: 'app-volunteers',
  templateUrl: './volunteers.component.html',
  styleUrls: ['./volunteers.component.css', '../app.component.css']
})
export class VolunteersComponent implements OnInit {

  volunteers: Volunteer[];

  constructor(private volunteerService: VolunteerService) { }
  
  ngOnInit(): void {
    this.getVolunteers();
  }

  getVolunteers(): void {
    this.volunteerService
      .getVolunteers()
      .subscribe(volunteers => this.volunteers = volunteers);
  }

}
