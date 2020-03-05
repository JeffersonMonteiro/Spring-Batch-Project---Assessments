import { Component, OnInit, Input } from '@angular/core';
import { Volunteer } from '../volunteer';

@Component({
  selector: 'app-volunteer-detail',
  templateUrl: './volunteer-detail.component.html',
  styleUrls: ['./volunteer-detail.component.css']
})
export class VolunteerDetailComponent implements OnInit {

  @Input() volunteer: Volunteer;
  constructor() { }

  ngOnInit(): void {
  }

}
