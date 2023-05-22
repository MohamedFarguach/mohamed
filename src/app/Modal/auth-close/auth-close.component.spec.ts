import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AuthCloseComponent } from './auth-close.component';

describe('AuthCloseComponent', () => {
  let component: AuthCloseComponent;
  let fixture: ComponentFixture<AuthCloseComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AuthCloseComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AuthCloseComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
