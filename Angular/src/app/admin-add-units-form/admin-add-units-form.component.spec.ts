import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AdminAddUnitsFormComponent } from './admin-add-units-form.component';

describe('AdminAddUnitsFormComponent', () => {
  let component: AdminAddUnitsFormComponent;
  let fixture: ComponentFixture<AdminAddUnitsFormComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AdminAddUnitsFormComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AdminAddUnitsFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
