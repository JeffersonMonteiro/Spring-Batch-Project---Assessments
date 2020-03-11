import { Component, OnInit } from '@angular/core';
import { NgbModalConfig, NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { Volunteer } from '../volunteer';
import {VolunteerService} from '../volunteer.service'

@Component({
  selector: 'app-volunteers',
  templateUrl: './volunteers.component.html',
  styleUrls: ['./volunteers.component.css', '../app.component.css'],
  providers: [NgbModalConfig, NgbModal]
})
export class VolunteersComponent implements OnInit {

  volunteers: Volunteer[];
  volunteer: Volunteer;

  constructor(private volunteerService: VolunteerService, config: NgbModalConfig, private modalService: NgbModal) {
    config.backdrop = 'static';
    config.keyboard = false;
  }

  ngOnInit(): void {
    this.getVolunteers();
  }

  getVolunteers(): void {
    this.volunteerService
      .getVolunteers()
      .subscribe(volunteers => this.volunteers = volunteers);
  }

  deleteVolunteer(id: number): void {
    console.log(id);
    this.volunteerService.deleteVolunteer(id).subscribe();
    window.location.reload();
  }

  open(content, volunteer: Volunteer) {
    this.volunteer = volunteer;
    this.modalService.open(content);
  }
}
