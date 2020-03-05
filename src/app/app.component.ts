import { Component, OnInit } from '@angular/core';

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
  //template:`

  ////<nav class="nav-teto">   
  ////   <h1>{{title}}</h1>
  ////</nav>
  ////<app-volunteers></app-volunteers>  
  ////`,
  styleUrls: ['./app.component.css']
})

export class AppComponent {
  title = 'Volunteer Management App';


}



