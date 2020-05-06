import { TestBed, async, inject } from '@angular/core/testing';

import { UserService } from './user.service';
import { 
  HttpClientTestingModule, HttpTestingController
} from '@angular/common/http/testing';
import { RouterTestingModule } from '@angular/router/testing';
import { User } from './user';

describe('UserService', () => {
  let service: UserService;
  let httpMock: HttpTestingController;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [
        RouterTestingModule,
        HttpClientTestingModule
      ]
    });
    service = TestBed.inject(UserService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
  
  it(`should get all users`, async(inject([HttpTestingController, UserService],
    (httpMock: HttpTestingController, service: UserService) => {

      const postItem = [
        {
          "idUser": 6,
          "name": "Amanda",
          "address": "address",
          "age": 22,
          "products": []
        },
        {
          "idUser": 7,
          "name": "Amanda",
          "address": "address",
          "age": 22,
          "products": []
        },
        {
          "idUser": 8,
          "name": "Ana",
          "address": "Address",
          "age": 22,
          "products": []
        }
      ];

      service.getUsers()
        .subscribe((posts: any) => {
          expect(posts.length).toBe(3);
        });

      let req = httpMock.expectOne('http://localhost:8080/User/get');
      expect(req.request.method).toBe("GET");

      req.flush(postItem);
      httpMock.verify();
    })));

    it(`should post a user`, async(inject([HttpTestingController, UserService],
      (httpMock: HttpTestingController, service: UserService) => {

        const postItem = [
          {
            "name": "Amanda",
            "address": "address",
            "age": 22
          }
        ];
        service.addUser({ "name":"Amanda", "address":"address", "age":22 } as User)
          .subscribe((posts: any) => {
            expect(posts.length).toBe(1);
          });
  
        let req = httpMock.expectOne('http://localhost:8080/User/add');
        expect(req.request.method).toBe("POST");
  
        req.flush(postItem);
        httpMock.verify();
      })));
});
