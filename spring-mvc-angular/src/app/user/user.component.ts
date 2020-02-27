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
  }

  getUsers(): void {
    this.userService.getUsers()
        .subscribe(users => this.users = users);
  }

  add(name: string, address: string, age:number): void {
    name = name.trim();
    address = address.trim();
    age = age.valueOf();
    if (!name) { return; }
    this.userService.addUser({ name, address, age } as User)
      .subscribe(hero => {
        this.users.push(hero);
      });
    this.location.back();
  }

  delete(user: User): void {
    this.users = this.users.filter(u => u !== user);
    this.userService.deleteUser(user).subscribe();
  }

}
