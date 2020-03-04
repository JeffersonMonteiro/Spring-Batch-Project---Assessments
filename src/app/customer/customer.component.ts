import { Router } from '@angular/router';
import { Customer } from './../customer';
import { Observable } from 'rxjs';
import { HttpService } from './../http.service';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-customer',
  templateUrl: './customer.component.html',
  styleUrls: ['./customer.component.css']
})
export class CustomerComponent implements OnInit {

  customers: Customer[];

  constructor(private httpService: HttpService,
    private router: Router) { }

  ngOnInit(): void {
    this.reloadData();
  }

  reloadData(): void {
    this.httpService.getCustomers().subscribe(customers => this.customers = customers);
  }

  deleteCustomer(customerId: number) {
    this.httpService.deleteCustomer(customerId)
    .subscribe(
      data => {
        console.log(data);
        this.reloadData();
      },
      error => console.log(error));
  }

  customerDetails(customerId: number){
    this.router.navigate(['details', customerId]);
  }



}
