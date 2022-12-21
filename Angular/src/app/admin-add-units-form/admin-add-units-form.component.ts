import { Component } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Bill } from '../bill';
import { BillServiceService } from '../bill-service.service';
import { Consumer } from '../consumer';

@Component({
  selector: 'app-admin-add-units-form',
  templateUrl: './admin-add-units-form.component.html',
  styleUrls: ['./admin-add-units-form.component.css'],
})
export class AdminAddUnitsFormComponent {
  AdminUserName!: string;
  AdminPassword!: string;
  consumerId!: number;
  year!: number;
  month!: string;
  unitsConsumed!: number;
  bill: Bill = new Bill(0, new Consumer(0, '', '', '', ''), 0, 0, '', 0);

  isHidden: boolean = false;
  addUnitsForm = new FormGroup({
    username: new FormControl('', [Validators.required]),
    password: new FormControl('', [Validators.required]),
    unitsConsumed: new FormControl('', [Validators.required]),
    consumerId: new FormControl('', [Validators.required]),
    year: new FormControl('', [Validators.required]),
    month: new FormControl('', [Validators.required]),
  });

  checkResultStatus() {
    return this.bill.billId != 0;
  }

  constructor(private billService: BillServiceService) {}

  onSubmit(data: any) {
    this.AdminUserName = data.username;
    this.AdminPassword = data.password;
    this.consumerId = data.consumerId;
    this.unitsConsumed = data.unitsConsumed;
    this.month = data.month;
    this.year = data.year;
    this.billService
      .addUnitsConsumed(
        this.AdminUserName,
        this.AdminPassword,
        this.month,
        this.year,
        this.consumerId,
        this.unitsConsumed
      )
      .subscribe((result) => (this.bill = result));
    console.log(this.bill.billId);
    this.isHidden = true;
  }

  onBack() {
    this.isHidden = !this.isHidden;
    this.bill = new Bill(0, new Consumer(0, '', '', '', ''), 0, 0, '', 0);
  }
}
