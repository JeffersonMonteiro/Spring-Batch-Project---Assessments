import { Component, OnInit, Input } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Location } from '@angular/common';

import { Volunteer } from '../volunteer';
import { VolunteerService } from '../volunteer.service';

@Component({
  selector: 'app-volunteer-detail',
  templateUrl: './volunteer-detail.component.html',
  styleUrls: ['./volunteer-detail.component.css', '../app.component.css']
})
export class VolunteerDetailComponent implements OnInit {

  volunteer: Volunteer;

  constructor(
    private route: ActivatedRoute,
    private volunteerService: VolunteerService,
    private location: Location
  ) {}

  ngOnInit(): void {
    this.getVolunteer();
  }

  getVolunteer(): void {
    const id = +this.route.snapshot.paramMap.get('id');
    this.volunteerService.getVolunteer(id).subscribe(volunteer => this.volunteer = volunteer);

  }

  goBack(): void {
    this.location.back();
  }
}
