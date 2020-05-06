import { Component, OnInit, Input } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { UserService } from '../user.service';
import { Product } from '../product';
import { Location } from '@angular/common';
import { User } from '../user';

@Component({
  selector: 'app-product',
  templateUrl: './product.component.html',
  styleUrls: ['./product.component.css']
})
export class ProductComponent implements OnInit {

  @Input() user: User;

  constructor(
    private route: ActivatedRoute,
    private userService: UserService,
    private location: Location) { }

  ngOnInit(): void {
    this.getUser();
    this.validElement();
  }
  
  validElement(): void {
    let element = document.getElementById("nameProduct");
    console.log('Name element', element);
    if(element != null){
      element.addEventListener("keypress", function () {
        element.className = 'form-control';
      });
    }    
  }

  goBack(): void {
    this.location.back();
  }

  getUser(): void {
    const id = +this.route.snapshot.paramMap.get('idUser');
    this.userService.getUser(id)
      .subscribe(user => this.user = user);
  }

  addProduct(name: string, id: number): void {
    let element = document.getElementById("nameProduct");
    if(name.trim()){
      this.userService.addProduct({ name } as Product, id).subscribe((result) => { });
      this.goBack();
    }else{
      if (!name) {
        element.className = 'form-control is-invalid';
      }
    }
  }

}
