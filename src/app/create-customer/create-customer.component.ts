import { Observable } from 'rxjs';
import { Router } from '@angular/router';
import { Customer } from './../customer';
import { HttpService } from './../http.service';
import { Component, OnInit } from '@angular/core';


@Component({
  selector: 'app-create-customer',
  templateUrl: './create-customer.component.html',
  styleUrls: ['./create-customer.component.css']
})
export class CreateCustomerComponent implements OnInit {

  customer: Customer = new Customer();
  submitted = false

  constructor(private httpService: HttpService, 
    private router: Router) { }

  ngOnInit(): void {
  }

  createCustomer(customer: Customer) {
    this.httpService.createCustomer(customer).subscribe();
  }

  onSubmit() {
    this.submitted = true;
  }

  gotoList() {
    this.router.navigate(['/customers']);
  }

  returnPage() {
    this.router.navigate(['/']);
  }
}
