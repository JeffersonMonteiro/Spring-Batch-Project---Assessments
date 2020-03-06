import { Component, OnInit } from '@angular/core';
import { Volunteer } from '../volunteer';
import { VolunteerService } from '../volunteer.service';
import { AppComponent } from '../app.component';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css', '../app.component.css']
})
export class DashboardComponent implements OnInit {
  volunteers: Volunteer[] = [];

  constructor(private volunteeerService: VolunteerService) { }

  ngOnInit() {
    this.getVolunteers();
  }

  getVolunteers(): void {
    this.volunteeerService.getVolunteers()
      .subscribe(volunteers => this.volunteers = this.volunteers.slice(1, 5));
  }
}
