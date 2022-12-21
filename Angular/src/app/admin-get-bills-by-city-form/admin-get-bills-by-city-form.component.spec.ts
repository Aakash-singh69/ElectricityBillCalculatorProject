import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AdminGetBillsByCityFormComponent } from './admin-get-bills-by-city-form.component';

describe('AdminGetBillsByCityFormComponent', () => {
  let component: AdminGetBillsByCityFormComponent;
  let fixture: ComponentFixture<AdminGetBillsByCityFormComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AdminGetBillsByCityFormComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AdminGetBillsByCityFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
