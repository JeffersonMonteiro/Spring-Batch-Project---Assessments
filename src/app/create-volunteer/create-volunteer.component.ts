import { Component, OnInit } from '@angular/core';
import { Volunteer } from '../volunteer';
import { VolunteerService } from '../volunteer.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-create-volunteer',
  templateUrl: './create-volunteer.component.html',
  styleUrls: ['./create-volunteer.component.css', '../app.component.css']
})
export class CreateVolunteerComponent implements OnInit {

  volunteer: Volunteer = new Volunteer();
  submitted = false;

  constructor(private volunteerService: VolunteerService, private router: Router) { }

  ngOnInit(): void {
  }

  onSubmit() {
    this.submitted = true;
  }

  createVolunteer(volunteer: Volunteer): void {
    console.log(volunteer);
    console.log(this.volunteerService);
    this.volunteerService.createVolunteer(volunteer).subscribe();
  }

  returnPage() {
    window.location.reload();
  }

}
