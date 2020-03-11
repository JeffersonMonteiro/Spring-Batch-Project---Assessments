import { Component, OnInit } from '@angular/core';
import { Person } from '../person';
import { PersonService } from '../person.service';
import { $ } from 'protractor';
import { NgbModalConfig, NgbModal } from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css'],
  providers: [NgbModalConfig, NgbModal]
})
export class DashboardComponent implements OnInit {
  people: Person[] = [];
  person: Person;

  constructor(
    private personService: PersonService,
    config: NgbModalConfig, 
    private modalService: NgbModal
    ) {
      config.backdrop = 'static';
      config.keyboard = false;
     }

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

  open(content, person: Person) {
    this.person = person; 
    this.modalService.open(content);
  }
}
