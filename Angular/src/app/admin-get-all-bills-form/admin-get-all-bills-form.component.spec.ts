import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AdminGetAllBillsFormComponent } from './admin-get-all-bills-form.component';

describe('AdminGetAllBillsFormComponent', () => {
  let component: AdminGetAllBillsFormComponent;
  let fixture: ComponentFixture<AdminGetAllBillsFormComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AdminGetAllBillsFormComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AdminGetAllBillsFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
