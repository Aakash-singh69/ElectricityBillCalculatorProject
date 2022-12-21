import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ConsumerRegistrationFormComponent } from './consumer-registration-form.component';

describe('ConsumerRegistrationFormComponent', () => {
  let component: ConsumerRegistrationFormComponent;
  let fixture: ComponentFixture<ConsumerRegistrationFormComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ConsumerRegistrationFormComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ConsumerRegistrationFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
