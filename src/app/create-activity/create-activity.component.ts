import { Component, OnInit } from '@angular/core';
import { Activity } from '../activity';
import { ActivityService } from '../activity.service';
import { Router, ActivatedRoute } from '@angular/router';
import { Location } from '@angular/common';

@Component({
  selector: 'app-create-activity',
  templateUrl: './create-activity.component.html',
  styleUrls: ['./create-activity.component.css', '../app.component.css']
})
export class CreateActivityComponent implements OnInit {

  activity: Activity = new Activity();
  submitted = false;

  constructor(private activityService: ActivityService, private router: Router, private location: Location, private route: ActivatedRoute) { }

  ngOnInit(): void {
  }

  onSubmit() {
    this.submitted = true;
  }

  createActivity(activity: Activity): void {
    const voluntId = +this.route.snapshot.paramMap.get('id');
    this.activityService.createActivity(activity, voluntId).subscribe();
    this.backPage();
  }

  returnPage() {
    window.location.reload();
  }

  backPage() {
    this.location.back();
  }

}
