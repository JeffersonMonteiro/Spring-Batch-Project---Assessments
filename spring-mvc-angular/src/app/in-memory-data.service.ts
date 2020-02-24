import { InMemoryDbService } from 'angular-in-memory-web-api';
import { Injectable } from '@angular/core';
import { User } from './user';

@Injectable({
  providedIn: 'root'
})
export class InMemoryDataService implements InMemoryDbService {
  createDb() {
    const users =[
      {userId: 1, name:'Mary', address:'Address', age:21},
      {userId: 2, name:'Edu', address:'Address', age:26},
      {userId: 3, name:'Cami', address:'Address', age:20}
    ];
    return {users};
  }
  genId(users: User[]): number {
    return users.length > 0 ? Math.max(...users.map(user => user.userId)) + 1 : 11;
  }
}
