import { Component, OnInit } from '@angular/core';

export class Volunteer {
  id: number;
  name: string;
  age: number;
  amntBuilding: number;
  amntSurvey: number;
  active: Boolean;
}

const VOLUNTEERS: Volunteer[] = [
  { id: 1, name: 'Lu Ribas', age: 29, amntBuilding: 17, amntSurvey: 4, active: true },
  { id: 2, name: 'Yuri Omura', age: 23, amntBuilding: 19, amntSurvey: 5, active: true },
  { id: 3, name: 'Thais Morais', age: 22, amntBuilding: 21, amntSurvey: 5, active: true },
  { id: 4, name: 'Adri Nunes', age: 34, amntBuilding: 14, amntSurvey: 3, active: true },
  { id: 5, name: 'Adi Thiele', age: 26, amntBuilding: 5, amntSurvey: 2, active: false },
  { id: 6, name: 'Helô Portugal', age: 23, amntBuilding: 157, amntSurvey: 4, active: true },
  { id: 7, name: 'Jully Pinheiro', age: 22, amntBuilding: 13, amntSurvey: 2, active: true },
  { id: 8, name: 'Lari Carneiro', age: 24, amntBuilding: 9, amntSurvey: 3, active: true },
  { id: 9, name: 'Lu Matos', age: 34, amntBuilding: 2, amntSurvey: 3, active: false },
  { id: 10, name: 'Duda Dircksen', age: 22, amntBuilding: 13, amntSurvey: 5, active: true}
];

@Component({
  selector: 'app-root',
  //templateUrl: './app.component.html',
  template: `
  <nav class="nav-teto">   
      <h1>{{title}}</h1>
  </nav>

  <h2> Volunteers List </h2>
  <ul class="volunts">
    <li *ngFor="let volunteer of volunteers" [class.selected]="volunteer===selectedVolunteer"
        (click)="onSelect(volunteer)">
       <span class="badge"> {{volunteer.id}} </span> {{volunteer.name}}
    </li>
  </ul>

  <div *ngIf="selectedVolunteer">
    <h3>{{selectedVolunteer.name}} details:</h3>
    <div><label>id: </label>{{selectedVolunteer.id}}</div>
    <div>
        <label>Name: </label>
        <input [(ngModel)]="selectedVolunteer.name" placeholder="name"/>
    </div>
    <div><label>Buildings: </label>
      <input [(ngModel)]="selectedVolunteer.amntBuilding" placeholder="buildings"/>
    </div>
    <div><label>Surveys: </label>
      <input [(ngModel)]="selectedVolunteer.amntSurvey" placeholder="surveys"/>
    </div>
  </div>
  
  `,
  styleUrls: ['./app.component.css']
})

export class AppComponent {
  title = 'Volunteer Management App';
  selectedVolunteer: Volunteer;
  volunteers = VOLUNTEERS;
  
  onSelect(volunteer: Volunteer): void {
    this.selectedVolunteer = volunteer;
  }

}



