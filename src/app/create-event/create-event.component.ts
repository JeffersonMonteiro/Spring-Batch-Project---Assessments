import { Event } from './../events';
import { Router, ActivatedRoute } from '@angular/router';
import { HttpService } from './../http.service';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-create-event',
  templateUrl: './create-event.component.html',
  styleUrls: ['./create-event.component.css']
})
export class CreateEventComponent implements OnInit {

  event: Event = new Event();
  submitted = false;

  constructor(private httpService: HttpService,
    private router: ActivatedRoute) { }

  ngOnInit(): void {
  }

  onSubmit() {
    this.submitted = true;
  }

  createEvent(event: Event): void {
    const customerId = +this.router.snapshot.paramMap.get('customerId');
    this.httpService.createEvent(event, customerId).subscribe();
  }

}
