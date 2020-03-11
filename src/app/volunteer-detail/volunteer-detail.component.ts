import { Component, OnInit, Input } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Location } from '@angular/common';

import { Volunteer } from '../volunteer';
import { VolunteerService } from '../volunteer.service';
import { NgbModalConfig, NgbModal } from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'app-volunteer-detail',
  templateUrl: './volunteer-detail.component.html',
  styleUrls: ['./volunteer-detail.component.css', '../app.component.css'],
  providers: [NgbModalConfig, NgbModal]
})
export class VolunteerDetailComponent implements OnInit {

  @Input() volunteer: Volunteer;
  submitted = false;

  constructor(
    private route: ActivatedRoute,
    private volunteerService: VolunteerService,
    private location: Location,
    config: NgbModalConfig, private modalService: NgbModal
  ) {
    config.backdrop = 'static';
    config.keyboard = false;
  }

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

  save(): void {
    this.volunteerService.updateVolunteer(this.volunteer).subscribe(() => this.goBack());
  }

  update(volunteer: Volunteer): void {
    const id = +this.route.snapshot.paramMap.get('id');
    this.volunteer = volunteer;
    this.volunteerService.updateVolunteer(this.volunteer).subscribe(() => this.goBack());
  }
  onSubmit() {
    this.submitted = true;
  }

  backPage() {
    this.location.back();
  }

  open(content, volunteer: Volunteer) {
    this.volunteer = volunteer;
    this.modalService.open(content);
  }

}
