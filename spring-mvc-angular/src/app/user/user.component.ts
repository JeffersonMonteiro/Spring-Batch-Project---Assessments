import { Component, OnInit } from '@angular/core';
import { User } from '../user';
import { UserService } from '../user.service'
import { Location } from '@angular/common';

@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.css']
})
export class UserComponent implements OnInit {

  users: User[];

  constructor(private userService: UserService, private location: Location) { }

  ngOnInit(): void {
    this.getUsers();
    this.validElement();
  }

  validElement(): void {
    let nameElement = document.getElementById("name");
    nameElement.addEventListener("keypress", function () {
      nameElement.className = 'form-control';
    })
    let addressElement = document.getElementById("address");
    addressElement.addEventListener("keypress", function () {
      addressElement.className = 'form-control';
    })
    let ageElement = document.getElementById("age");
    ageElement.addEventListener("keypress", function () {
      ageElement.className = 'form-control';
    })
  }

  getUsers(): void {
    this.userService.getUsers()
      .subscribe(users => this.users = users);
  }

  add(name: string, address: string, age: number): void {
    let nameElement = document.getElementById("name");
    let addressElement = document.getElementById("address");
    let ageElement = document.getElementById("age");
    if(age.valueOf() && address.trim() && name.trim()){
      this.userService.addUser({ name, address, age } as User)
      .subscribe(user => {
        this.users.push(user);
      });
      this.location.back();
    }else{
      if (!name) {
        nameElement.className = 'form-control is-invalid';
      }
      if (!address) {
        addressElement.className = 'form-control is-invalid';
      }
      if (!age) {
        ageElement.className = 'form-control is-invalid';
      }
    }
  }

}
