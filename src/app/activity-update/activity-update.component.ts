import { Component, OnInit, Input } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { ActivityService } from '../activity.service';
import { NgbModalConfig, NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { Location } from '@angular/common';
import { Activity } from '../activity';
import { VolunteerService } from '../volunteer.service';
import { Volunteer } from '../volunteer';

@Component({
  selector: 'app-activity-update',
  templateUrl: './activity-update.component.html',
  styleUrls: ['./activity-update.component.css', '../app.component.css']
})
export class ActivityUpdateComponent implements OnInit {

  volunteer: Volunteer;
  activity: Activity;
  submitted = false;

  constructor(private route: ActivatedRoute,
      private activityService:ActivityService,
    private location: Location,
    config: NgbModalConfig,
    private modalService: NgbModal,
    //private activity: Activity,
    private volunteerService: VolunteerService
  ) { }

  ngOnInit(): void {
    this.getVolunteer();
    this.getActivity();
  }

  updateActivity(activity: Activity): void {
    this.activity = activity;
    this.activityService.updateActivity(this.activity).subscribe(() => this.goBack());
  }

  getVolunteer(): void {
    const id = +this.route.snapshot.paramMap.get('id');
    this.volunteerService.getVolunteer(id).subscribe(volunteer => this.volunteer = volunteer);
  }

  getActivity(): void {
    const id = +this.route.snapshot.paramMap.get('activityId');
    this.activityService.getActivity(id).subscribe(activity => this.activity = activity);
  }

  goBack(): void {
    this.location.back();
  }

  onSubmit() {
    this.submitted = true;
  }

  backPage() {
    window.history.back();
  }
}
