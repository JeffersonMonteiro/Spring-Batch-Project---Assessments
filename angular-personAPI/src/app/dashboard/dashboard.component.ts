import { Component, OnInit } from '@angular/core';
import { Person } from '../person';
import { PersonService } from '../person.service';
import { $ } from 'protractor';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit {
  people: Person[] = [];

  constructor(private personService: PersonService) { }

  ngOnInit() {
    this.getPeople();

  }

  getPeople(): void {
    this.personService.getPeople()
    .subscribe(people => this.people = people);
  }
  
  
  delete(person: Person): void {
    this.people = this.people.filter(p => p !== person);
    this.personService.deletePerson(person).subscribe();
  }
}
