import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import { RouterTestingModule } from '@angular/router/testing';
import { HttpClientModule } from '@angular/common/http';

import { CreateVolunteerComponent } from './create-volunteer.component';

describe('CreateVolunteerComponent', () => {
  let component: CreateVolunteerComponent;
  let fixture: ComponentFixture<CreateVolunteerComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [CreateVolunteerComponent],
      imports: [
        RouterTestingModule,
        HttpClientModule
      ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CreateVolunteerComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  describe('createVolunteer', () => {
    it('should return error if age under 18', () => {
      
    })
  })


});
