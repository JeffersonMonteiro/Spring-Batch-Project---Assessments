import { TestBed, async, inject } from '@angular/core/testing';
import { RouterTestingModule } from '@angular/router/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';

import { VolunteerService } from './volunteer.service';
import { NgModule } from '@angular/core';
import { Volunteer } from './volunteer';
import { empty } from 'rxjs';
import { Activity } from './activity';

describe('VolunteerService', () => {
  let service: VolunteerService;
  let httpMock: HttpTestingController;

  beforeEach(() => {
    // implement testing module with instance of service
    TestBed.configureTestingModule({
      providers: [
        VolunteerService
      ],
      imports: [
        RouterTestingModule,
        // includes mock implementation of http service and allows ruturn data desired for test
        HttpClientTestingModule
      ]
    });
    service = TestBed.get(VolunteerService);
    httpMock = TestBed.get(HttpTestingController);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });

  it('given 2 volunteers when getVolunteers invoked should retrieve all 2 volunteers', async(inject([HttpTestingController, VolunteerService],
    (httpClient: HttpTestingController, service: VolunteerService) => {

      const volunteersItem = [
        {
          "id": 1,
          "name": "Lu Ribas",
          "age": 29,
          "amntBuilding": 17,
          "amntSurvey": 4,
          "active": true,
          "activityList": Activity[0],
        },
        {
          "id": 2,
          "name": "Thais Morais",
          "age": 23,
          "amntBuilding": 17,
          "amntSurvey": 6,
          "active": true,
          "activityList": Activity[0],
        }
      ];

      service.getVolunteers().subscribe((volunteers: Volunteer[]) => {
        expect(volunteers.length).toBe(2);
        expect(volunteers).toEqual(volunteersItem);
        // comparar atributos especificos de cada
      });

      let req = httpMock.expectOne('http://localhost:8080/volunteer/get');
      expect(req.request.method).toBe("GET");

      httpMock.verify();

    })));

  it(`given volunteer should post the volunteer`, async(inject([HttpTestingController, VolunteerService],
    (httpMock: HttpTestingController, service: VolunteerService) => {

      const volunteerItem = [
        {
          "name": "Lu Ribas",
          "age": 29,
          "amntBuilding": 17,
          "amntSurvey": 4,
        }
      ];
      service.createVolunteer({ "name": "Lu Ribas", "age": 29, "amntBuilding": 17, "amntSurvey": 4} as Volunteer)
        .subscribe((volunteer: any) => {
          expect(volunteer.length).toBe(1);
          expect(volunteer).toEqual(volunteerItem);
          // comparar atributos especificos de cada

          });

        let req = httpMock.expectOne('http://localhost:8080/volunteer/add');
        expect(req.request.method).toBe("POST");

 
        httpMock.verify();
    })));

  it(`given volunteer posted should delete the volunteer`, async(inject([HttpTestingController, VolunteerService],
    (httpMock: HttpTestingController, service: VolunteerService) => {

      service.deleteVolunteer(1).subscribe();

      let req = httpMock.expectOne('http://localhost:8080/volunteer/delete/1');
      expect(req.request.method).toBe("DELETE");

      httpMock.verify();
    })));


  });


