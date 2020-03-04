import { HttpService } from '../http.service';
import { Component, OnInit } from '@angular/core';


@Component({
  selector: 'app-event',
  templateUrl: './event.component.html',
  styleUrls: ['./event.component.css']
})
export class EventComponent implements OnInit {

  events: Object;

  constructor(private _http: HttpService) { }

  ngOnInit() {

    
  }

}
