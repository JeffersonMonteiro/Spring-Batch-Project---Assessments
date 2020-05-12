import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import { RouterTestingModule } from "@angular/router/testing";
import { HttpClientModule } from '@angular/common/http';


import { ActivityUpdateComponent } from './activity-update.component';
import { ActivityService } from '../activity.service';
import { Observable } from 'rxjs';
//import 'rxjs/add/observable/from';
//import 'rxjs/add/observable/empty';

describe('ActivityUpdateComponent', () => {
  let component: ActivityUpdateComponent;
  let fixture: ComponentFixture<ActivityUpdateComponent>;
  let service = ActivityService;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ActivityUpdateComponent],
      imports: [
        RouterTestingModule,
        HttpClientModule
      ],
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ActivityUpdateComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  //it('should call server to save changes when a new actvity is created', () => {
  //  let spy = spyOn(service, 'updateActivity').and.callFake(a => {
  //    return Observable.empty();
  //  });

  //  component.updateActivity();

  //  expect(spy).toHaveBeenCalled();
  //});
});
