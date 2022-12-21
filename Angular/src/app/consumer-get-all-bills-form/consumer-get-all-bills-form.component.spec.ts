import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ConsumerGetAllBillsFormComponent } from './consumer-get-all-bills-form.component';

describe('ConsumerGetAllBillsFormComponent', () => {
  let component: ConsumerGetAllBillsFormComponent;
  let fixture: ComponentFixture<ConsumerGetAllBillsFormComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ConsumerGetAllBillsFormComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ConsumerGetAllBillsFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
