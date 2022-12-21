// import { Component } from '@angular/core';

// @Component({
//   selector: 'app-admin-get-bills-by-year-form',
//   templateUrl: './admin-get-bills-by-year-form.component.html',
//   styleUrls: ['./admin-get-bills-by-year-form.component.css']
// })
// export class AdminGetBillsByYearFormComponent {

// }
import { Component } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Bill } from '../bill';
import { BillServiceService } from '../bill-service.service';

@Component({
  selector: 'app-admin-get-bills-by-year-form',
  templateUrl: './admin-get-bills-by-year-form.component.html',
  styleUrls: ['./admin-get-bills-by-year-form.component.css'],
})
export class AdminGetBillsByYearFormComponent {
  userName!: string;
  password!: string;
  year!: number;
  Bills: Bill[] = [];
  isHidden: boolean = false;
  checkBillsStatus() {
    return this.Bills.length != 0;
  }
  userForm = new FormGroup({
    userName: new FormControl('', [Validators.required]),
    password: new FormControl('', [Validators.required]),
    year: new FormControl('', [Validators.required]),
  });

  constructor(private billService: BillServiceService) {}

  onSubmit(data: any) {
    this.userName = data.userName;
    this.password = data.password;
    this.year = data.year;
    this.billService
      .getBillsByYearAdmin(this.userName, this.password, this.year)
      .subscribe((bills) => (this.Bills = bills));
    this.isHidden = true;
  }
  get getUsername() {
    return this.userForm.get('userName');
  }

  get getPassword() {
    return this.userForm.get('password');
  }

  get getYear() {
    return this.userForm.get('year');
  }
  onBack() {
    this.isHidden = !this.isHidden;
    this.Bills = [];
  }
}
