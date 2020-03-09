import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, FormArray } from '@angular/forms';

export class Volunteer {
  id: number;
  name: string;
  age: number;
  amntBuilding: number;
  amntSurvey: number;
  active: Boolean;
}

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})

export class AppComponent {
  title = 'Volunteer Management App';


}



