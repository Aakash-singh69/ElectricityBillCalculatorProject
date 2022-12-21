import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ConsumerGetBillsByYearFormComponent } from './consumer-get-bills-by-year-form.component';

describe('ConsumerGetBillsByYearFormComponent', () => {
  let component: ConsumerGetBillsByYearFormComponent;
  let fixture: ComponentFixture<ConsumerGetBillsByYearFormComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ConsumerGetBillsByYearFormComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ConsumerGetBillsByYearFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
