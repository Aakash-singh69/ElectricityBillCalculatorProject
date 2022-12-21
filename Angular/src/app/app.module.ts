import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { AdminDashboardComponent } from './admin-dashboard/admin-dashboard.component';
import { ConsumerDashboardComponent } from './consumer-dashboard/consumer-dashboard.component';
import { AdminGetAllBillsFormComponent } from './admin-get-all-bills-form/admin-get-all-bills-form.component';
import { AdminGetBillsByYearFormComponent } from './admin-get-bills-by-year-form/admin-get-bills-by-year-form.component';
import { AdminGetBillsByMonthFormComponent } from './admin-get-bills-by-month-form/admin-get-bills-by-month-form.component';
import { AdminGetBillsByAreaFormComponent } from './admin-get-bills-by-area-form/admin-get-bills-by-area-form.component';
import { AdminGetBillsByCityFormComponent } from './admin-get-bills-by-city-form/admin-get-bills-by-city-form.component';
import { ConsumerGetBillsByYearFormComponent } from './consumer-get-bills-by-year-form/consumer-get-bills-by-year-form.component';
import { ConsumerGetBillsByMonthFormComponent } from './consumer-get-bills-by-month-form/consumer-get-bills-by-month-form.component';
import { ConsumerGetAllBillsFormComponent } from './consumer-get-all-bills-form/consumer-get-all-bills-form.component';
import { ConsumerRegistrationFormComponent } from './consumer-registration-form/consumer-registration-form.component';
import { AdminAddUnitsFormComponent } from './admin-add-units-form/admin-add-units-form.component';
import { HttpClientModule } from '@angular/common/http';
import { ReactiveFormsModule } from '@angular/forms';

@NgModule({
  declarations: [
    AppComponent,
    AdminDashboardComponent,
    ConsumerDashboardComponent,
    AdminGetAllBillsFormComponent,
    AdminGetBillsByYearFormComponent,
    AdminGetBillsByMonthFormComponent,
    AdminGetBillsByAreaFormComponent,
    AdminGetBillsByCityFormComponent,
    ConsumerGetBillsByYearFormComponent,
    ConsumerGetBillsByMonthFormComponent,
    ConsumerGetAllBillsFormComponent,
    ConsumerRegistrationFormComponent,
    AdminAddUnitsFormComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    ReactiveFormsModule,
  ],
  providers: [],
  bootstrap: [AppComponent],
})
export class AppModule {}
