import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import { RouterTestingModule } from '@angular/router/testing';
import { HttpClientModule } from '@angular/common/http';

import { VolunteerDetailComponent } from './volunteer-detail.component';

describe('VolunteerDetailComponent', () => {
  let component: VolunteerDetailComponent;
  let fixture: ComponentFixture<VolunteerDetailComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [VolunteerDetailComponent],
      imports: [
        RouterTestingModule,
        HttpClientModule
        ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(VolunteerDetailComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
