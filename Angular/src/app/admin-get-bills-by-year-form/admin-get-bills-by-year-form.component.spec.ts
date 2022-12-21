import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AdminGetBillsByYearFormComponent } from './admin-get-bills-by-year-form.component';

describe('AdminGetBillsByYearFormComponent', () => {
  let component: AdminGetBillsByYearFormComponent;
  let fixture: ComponentFixture<AdminGetBillsByYearFormComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AdminGetBillsByYearFormComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AdminGetBillsByYearFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
