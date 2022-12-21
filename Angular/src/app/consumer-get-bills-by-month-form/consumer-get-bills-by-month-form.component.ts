// import { Component } from '@angular/core';

// @Component({
//   selector: 'app-consumer-get-bills-by-month-form',
//   templateUrl: './consumer-get-bills-by-month-form.component.html',
//   styleUrls: ['./consumer-get-bills-by-month-form.component.css']
// })
// export class ConsumerGetBillsByMonthFormComponent {

// }
import { Component } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Bill } from '../bill';
import { BillServiceService } from '../bill-service.service';

@Component({
  selector: 'app-consumer-get-bills-by-month-form',
  templateUrl: './consumer-get-bills-by-month-form.component.html',
  styleUrls: ['./consumer-get-bills-by-month-form.component.css'],
})
export class ConsumerGetBillsByMonthFormComponent {
  month!: string;
  year!: number;
  consumerId!: number;
  isHidden: boolean = false;
  Bills: Bill[] = [];

  checkBillsStatus() {
    return this.Bills.length != 0;
  }
  consumerForm = new FormGroup({
    consumerId: new FormControl('', [Validators.required]),
    month: new FormControl('', [Validators.required]),
    year: new FormControl('', [Validators.required]),
  });
  constructor(private billService: BillServiceService) {}

  onSubmit(data: any) {
    this.month = data.month;
    this.year = data.year;
    this.consumerId = data.consumerId;
    this.billService
      .getBillsByMonthConsumer(this.consumerId, this.month, this.year)
      .subscribe((bills) => (this.Bills = bills));
    this.isHidden = true;
  }
  get getConsumerId() {
    return this.consumerForm.get('consumerId');
  }
  get getMonth() {
    return this.consumerForm.get('month');
  }

  get getYear() {
    return this.consumerForm.get('year');
  }
  onBack() {
    this.isHidden = !this.isHidden;
    this.Bills = [];
  }
}
