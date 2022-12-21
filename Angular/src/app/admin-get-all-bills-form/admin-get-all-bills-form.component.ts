// import { Component } from '@angular/core';
// import { FormControl, FormGroup, Validators } from '@angular/forms';
// import { Bill } from '../bill';
// import { BillServiceService } from '../bill-service.service';

// @Component({
//   selector: 'app-admin-get-all-bills-form',
//   templateUrl: './admin-get-all-bills-form.component.html',
//   styleUrls: ['./admin-get-all-bills-form.component.css'],
// })
// export class AdminGetAllBillsFormComponent {
//   AdminUserName!: string;
//   AdminPassword!: string;
//   Bills: Bill[] = [];
//   isHidden: boolean = false;
//   checkBillsStatus() {
//     return this.Bills.length != 0;
//   }
//   userForm = new FormGroup({
//     username: new FormControl('', [Validators.required]),
//     password: new FormControl('', [Validators.required]),
//   });

//   constructor(private billService: BillServiceService) {}

//   onSubmit(data: any) {
//     this.AdminUserName = data.username;
//     this.AdminPassword = data.password;
//     this.billService
//       .getAllBillsAdmin(this.AdminUserName, this.AdminPassword)
//       .subscribe((bills) => (this.Bills = bills));
//     this.isHidden = true;
//   }

//   onBack() {
//     this.isHidden = !this.isHidden;
//     this.Bills = [];
//   }

//   get getUserName() {
//     return this.userForm.get('username');
//   }
//   get getPassword() {
//     return this.userForm.get('password');
//   }
// }
import { Component } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Bill } from '../bill';
import { BillServiceService } from '../bill-service.service';

@Component({
  selector: 'app-admin-get-all-bills-form',
  templateUrl: './admin-get-all-bills-form.component.html',
  styleUrls: ['./admin-get-all-bills-form.component.css'],
})
export class AdminGetAllBillsFormComponent {
  userName!: string;
  password!: string;
  Bills: Bill[] = [];
  isHidden: boolean = false;
  checkBillsStatus() {
    return this.Bills.length != 0;
  }
  userForm = new FormGroup({
    userName: new FormControl('', [Validators.required]),
    password: new FormControl('', [Validators.required]),
  });

  constructor(private billService: BillServiceService) {}

  onSubmit(data: any) {
    this.userName = data.userName;
    this.password = data.password;
    console.log(this.userName);
    this.billService
      .getAllBillsAdmin(this.userName, this.password)
      .subscribe((bills) => (this.Bills = bills));
    this.isHidden = true;
  }

  get getUsername() {
    return this.userForm.get('userName');
  }

  get getPassword() {
    return this.userForm.get('password');
  }

  onBack() {
    this.isHidden = !this.isHidden;
    this.Bills = [];
  }
}
