import { Customer } from './../customer';
import { ActivatedRoute } from '@angular/router';
import { HttpService } from './../http.service';
import { Component, OnInit, Input } from '@angular/core';

@Component({
  selector: 'app-event-details',
  templateUrl: './event-details.component.html',
  styleUrls: ['./event-details.component.css']
})
export class EventDetailsComponent implements OnInit {

  @Input() customer: Customer;

  constructor(private httpService: HttpService,
    private router: ActivatedRoute) { }

  ngOnInit(): void {
    this.getCustomer();
  }

  getCustomer(): void {
    const customerId = +this.router.snapshot.paramMap.get('customerId');
    this.httpService.getCustomer(customerId)
    .subscribe(customer => this.customer = customer)
  }

}
