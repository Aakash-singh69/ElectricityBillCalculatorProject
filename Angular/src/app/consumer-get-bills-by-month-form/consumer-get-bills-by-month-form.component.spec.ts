import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ConsumerGetBillsByMonthFormComponent } from './consumer-get-bills-by-month-form.component';

describe('ConsumerGetBillsByMonthFormComponent', () => {
  let component: ConsumerGetBillsByMonthFormComponent;
  let fixture: ComponentFixture<ConsumerGetBillsByMonthFormComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ConsumerGetBillsByMonthFormComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ConsumerGetBillsByMonthFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
