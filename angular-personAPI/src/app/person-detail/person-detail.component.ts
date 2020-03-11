import { Component, OnInit, Input } from '@angular/core';
import { Person } from '../person';

import { ActivatedRoute } from '@angular/router';
import { Location } from '@angular/common';

import { PersonService }  from '../person.service';
import { Product } from '../product';
import { NgbModal, NgbModalConfig } from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'app-person-detail',
  templateUrl: './person-detail.component.html',
  styleUrls: ['./person-detail.component.css'],
  providers: [NgbModalConfig, NgbModal]
})
export class PersonDetailComponent implements OnInit {
  
  @Input() person: Person;
  products: Product[];
  product: Product;

  constructor(
    private route: ActivatedRoute,
    private personService: PersonService,
    private location: Location,
    config: NgbModalConfig, 
    private modalService: NgbModal
  ) 
  { 
    config.backdrop = 'static';
    config.keyboard = false;
  }

  ngOnInit(): void {
    this.getPerson();
  }

  getPerson(): void {
    const id = +this.route.snapshot.paramMap.get('id');
    this.personService.getPerson(id)
    .subscribe(person => this.person = person);
  }

  goBack(): void {
    this.location.back();
  }

  save(name: string, age: number): void {
    name = name.trim();
    age = age.valueOf();
    if (!name) { 
      alert("Please enter a valid name");
      return; }
    if(age < 0 || age > 150 || !age){ return alert("Please enter a valid age value"); }
    this.personService.updatePerson(this.person)
      .subscribe(() => this.goBack());
  }

  addProduct(name: string, personId: number): void {
    name = name.trim();
    if (!name) { 
      alert("Please enter a valid name");
      return; }
    this.personService.addProduct({ name } as Product, personId)
      .subscribe((result) => {});
      window.location.reload();
  }

  deleteProduct(personId: number, product: Product): void {
    this.products = this.person.products.filter(p => p !== product);
    this.personService.deleteProduct(personId, product).subscribe();
    window.location.reload();
  }

  open(content, product: Product) {
    this.product = product; 
    this.modalService.open(content);
  }
}
