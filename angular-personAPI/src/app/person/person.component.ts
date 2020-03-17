import { Component, OnInit } from '@angular/core';
import { Person } from '../person';
import { PersonService } from '../person.service';
import { Product } from '../product';
import { Location } from '@angular/common';

@Component({
  selector: 'app-person',
  templateUrl: './person.component.html',
  styleUrls: ['./person.component.css']
})

export class PersonComponent implements OnInit {

  people: Person[];

  constructor(
    private personService: PersonService,
    private location: Location
  ) { }

  ngOnInit() {
    this.getPeople();
  }

  getPeople(): void {
    this.personService.getPeople()
      .subscribe(people => this.people = people);
  }

  add(name: string, age: number): void {
    name = name.trim();
    age = age.valueOf();
    if (!name) { 
      alert("Please enter a valid name");
      return; }
    if(name.length > 22){
      alert("You have exceeded the maximum character limit(22)")
      return;
    }
    if(age < 0 || age > 150 || !age){ return alert("Please enter a valid age value"); }
    this.personService.addPerson({ name, age } as Person)
      .subscribe(person => {
        this.people.push(person);
      });
  }

  delete(person: Person): void {
    this.people = this.people.filter(p => p !== person);
    this.personService.deletePerson(person).subscribe();
  }

  goBack(): void {
    this.location.back();
  }

}
