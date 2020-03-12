import { Component, OnInit, Input } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { NgbModalConfig, NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { Location } from '@angular/common';

import { ActivityService } from '../activity.service';
import { Activity } from '../activity';
import { Volunteer } from '../volunteer';
import { VolunteerService } from '../volunteer.service';

@Component({
  selector: 'app-activity-detail',
  templateUrl: './activity-detail.component.html',
  styleUrls: ['./activity-detail.component.css', '../app.component.css'],
  providers: [NgbModalConfig, NgbModal]
})
export class ActivityDetailComponent implements OnInit {

  @Input() volunteer: Volunteer;
  @Input() activity: Activity;

  constructor(private volunteerService: VolunteerService,
    private route: ActivatedRoute,
    config: NgbModalConfig,
    private modalService: NgbModal,
    private location: Location,
    private activityService: ActivityService) {
      config.backdrop = 'static';
      config.keyboard = false;
  }

  ngOnInit(): void {
    this.getVolunteer();
    console.log(this.volunteer);
  }

  getVolunteer(): void {
    const id = +this.route.snapshot.paramMap.get('id');
    this.volunteerService.getVolunteer(id).subscribe(volunteer => this.volunteer = volunteer);
  }

  deleteActivity(id: number) {
    const voluntId = +this.route.snapshot.paramMap.get('id');
    this.activityService.deleteActivity(id, voluntId).subscribe();
    window.location.reload();
  }

  open(content, activity: Activity) {
    this.activity = activity;
    this.modalService.open(content);
  }

  goBack(): void {
    this.location.back();
  }

  backPage() {
    window.history.back();
  }
}
