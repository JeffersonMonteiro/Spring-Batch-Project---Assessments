import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { UserComponent } from './user.component';
import { RouterTestingModule } from '@angular/router/testing';
import { 
  HttpClientTestingModule
} from '@angular/common/http/testing';

describe('UserComponent', () => {
  let component: UserComponent;
  let location: Location;
  let fixture: ComponentFixture<UserComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      imports: [ 
        HttpClientTestingModule, 
        RouterTestingModule
      ],
      declarations: [ UserComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(UserComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
