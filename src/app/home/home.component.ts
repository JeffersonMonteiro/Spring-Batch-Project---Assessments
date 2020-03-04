import { Event } from './../events';
import { HttpService } from './../http.service';
import { CustomerDetailsComponent } from './../customer-details/customer-details.component';
import { Component, OnInit } from '@angular/core';
import { Observable } from "rxjs";
import { Router } from '@angular/router';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  events: Observable<Event[]>;
  

  constructor() { }

  ngOnInit(): void {
  }

}
