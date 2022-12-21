import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AdminGetBillsByMonthFormComponent } from './admin-get-bills-by-month-form.component';

describe('AdminGetBillsByMonthFormComponent', () => {
  let component: AdminGetBillsByMonthFormComponent;
  let fixture: ComponentFixture<AdminGetBillsByMonthFormComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AdminGetBillsByMonthFormComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AdminGetBillsByMonthFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
