import { Component } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Bill } from '../bill';
import { BillServiceService } from '../bill-service.service';

@Component({
  selector: 'app-admin-get-bills-by-city-form',
  templateUrl: './admin-get-bills-by-city-form.component.html',
  styleUrls: ['./admin-get-bills-by-city-form.component.css'],
})
export class AdminGetBillsByCityFormComponent {
  AdminUserName!: string;
  AdminPassword!: string;
  city!: string;
  Bills: Bill[] = [];
  isHidden: boolean = false;
  checkBillsStatus() {
    return this.Bills.length != 0;
  }
  userForm = new FormGroup({
    username: new FormControl('', [Validators.required]),
    password: new FormControl('', [Validators.required]),
    city: new FormControl('', [Validators.required]),
  });

  constructor(private billService: BillServiceService) {}

  onSubmit(data: any) {
    this.AdminUserName = data.username;
    this.AdminPassword = data.password;
    this.city = data.city;
    this.billService
      .getBillsByCityAdmin(this.AdminUserName, this.AdminPassword, this.city)
      .subscribe((bills) => (this.Bills = bills));
    this.isHidden = true;
  }

  get getUserName() {
    return this.userForm.get('username');
  }
  get getPassword() {
    return this.userForm.get('password');
  }
  get getCity() {
    return this.userForm.get('city');
  }
  onBack() {
    this.isHidden = !this.isHidden;
    this.Bills = [];
  }
}
