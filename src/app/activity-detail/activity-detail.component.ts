import { Component, OnInit, Input } from '@angular/core';
import { Volunteer } from '../volunteer';
import { VolunteerService } from '../volunteer.service';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-activity-detail',
  templateUrl: './activity-detail.component.html',
  styleUrls: ['./activity-detail.component.css', '../app.component.css']
})
export class ActivityDetailComponent implements OnInit {

  @Input() volunteer: Volunteer;

  constructor( private volunteerService: VolunteerService, private route: ActivatedRoute) { }

  ngOnInit(): void {
    this.getVolunteer();
    console.log(this.volunteer);
  }

  getVolunteer(): void {
    const id = +this.route.snapshot.paramMap.get('id');
    this.volunteerService.getVolunteer(id).subscribe(volunteer => this.volunteer = volunteer);

  }

}
