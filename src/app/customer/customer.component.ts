import { Router } from '@angular/router';
import { Customer } from './../customer';
import { Observable } from 'rxjs';
import { HttpService } from './../http.service';
import { Component, OnInit } from '@angular/core';
import { NgbModalConfig, NgbModal } from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'app-customer',
  templateUrl: './customer.component.html',
  styleUrls: ['./customer.component.css'],
  providers: [NgbModalConfig, NgbModal]
})
export class CustomerComponent implements OnInit {

  customers: Customer[];

  customer: Customer;

  constructor(private httpService: HttpService,
    private router: Router, config: NgbModalConfig, private modalService: NgbModal) {
      config.backdrop = 'static';
      config.keyboard = false;
     }

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

  open(content, customer: Customer) {
    this.customer = customer;
    this.modalService.open(content);
  }

}
