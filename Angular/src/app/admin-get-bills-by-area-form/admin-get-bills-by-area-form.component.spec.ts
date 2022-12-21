import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AdminGetBillsByAreaFormComponent } from './admin-get-bills-by-area-form.component';

describe('AdminGetBillsByAreaFormComponent', () => {
  let component: AdminGetBillsByAreaFormComponent;
  let fixture: ComponentFixture<AdminGetBillsByAreaFormComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AdminGetBillsByAreaFormComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AdminGetBillsByAreaFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
