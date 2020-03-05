import { Router, ActivatedRoute } from '@angular/router';
import { HttpService } from './../http.service';
import { Component, OnInit, Input } from '@angular/core';
import { Customer } from '../customer';
import { Location } from '@angular/common';
import { FormControl } from '@angular/forms';

@Component({
  selector: 'app-customer-details',
  templateUrl: './customer-details.component.html',
  styleUrls: ['./customer-details.component.css']
})
export class CustomerDetailsComponent implements OnInit {

  @Input() customer: Customer;
  updated = false

  position = new FormControl('');

  constructor(private httpService: HttpService,
    private router: ActivatedRoute,
    private route: Router,
    private location: Location) { }

  ngOnInit(): void {
    this.getCustomer();
   
  }

  onUpdated() {
    this.updated = true;
  }

  goBack(): void {
    this.location.back();
  }

  gotoList() {
    this.route.navigate(['/customers']);
  }

  getCustomer(): void {
    const customerId = +this.router.snapshot.paramMap.get('customerId');
    this.httpService.getCustomer(customerId)
    .subscribe(customer => this.customer = customer)
  }

  save(): void {
    this.httpService.updateCustomer(this.customer)
    .subscribe();
    this.gotoList();
  }



}
