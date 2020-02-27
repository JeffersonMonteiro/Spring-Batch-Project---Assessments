import {Component, OnInit} from '@angular/core';
import {Person} from './person';
import {PersonService} from './person.service';

@Component({
      selector: 'app-person',
      templateUrl: './person.component.html',
      styleUrls: ['./person.component.css']
      })
export class PersonComponent implements OnInit{

    people: Person[];
    person = new Person();
    constructor(private _personService: PersonService){}

    ngOnInit(): void{
        this.getPerson();
    }

    getPerson(): void{
        this._personService.getAllPerson()
            .subscribe(people => {
                this.people = people,
                console.log(people)
            }, (error) => {
                console.log(error);
            });
    }

    addPerson(): void{
        this._personService.addPerson(this.person)
            .subscribe((response) => {
                this.reset();
                this.getPerson();
            }, (error) => {
                console.log(error);
            });
    }

    private reset(){
        this.person.id = null;
        this.person.name = null;
        this.person.age = null;
    }

    deletePerson(personId: string){
        this._personService.deletePerson(personId)
            .subscribe((response) => { console.log(response); this.getPerson();}, (error) => {
                console.log(error)
            })
    }

    updatePerson(personId: string){
        this._personService.updatePerson(personId)
            .subscribe((personData) => {this.person = personData; this.getPerson();}, (error) => {
                console.log(error)
            })
    }
}
