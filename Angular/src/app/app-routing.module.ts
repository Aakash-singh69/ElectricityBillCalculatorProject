import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AdminAddUnitsFormComponent } from './admin-add-units-form/admin-add-units-form.component';
import { AdminDashboardComponent } from './admin-dashboard/admin-dashboard.component';
import { AdminGetAllBillsFormComponent } from './admin-get-all-bills-form/admin-get-all-bills-form.component';
import { AdminGetBillsByAreaFormComponent } from './admin-get-bills-by-area-form/admin-get-bills-by-area-form.component';
import { AdminGetBillsByCityFormComponent } from './admin-get-bills-by-city-form/admin-get-bills-by-city-form.component';
import { AdminGetBillsByMonthFormComponent } from './admin-get-bills-by-month-form/admin-get-bills-by-month-form.component';
import { AdminGetBillsByYearFormComponent } from './admin-get-bills-by-year-form/admin-get-bills-by-year-form.component';
import { ConsumerDashboardComponent } from './consumer-dashboard/consumer-dashboard.component';
import { ConsumerGetAllBillsFormComponent } from './consumer-get-all-bills-form/consumer-get-all-bills-form.component';
import { ConsumerGetBillsByMonthFormComponent } from './consumer-get-bills-by-month-form/consumer-get-bills-by-month-form.component';
import { ConsumerGetBillsByYearFormComponent } from './consumer-get-bills-by-year-form/consumer-get-bills-by-year-form.component';
import { ConsumerRegistrationFormComponent } from './consumer-registration-form/consumer-registration-form.component';

const routes: Routes = [
  {
    path: 'admin-dashboard',
    component: AdminDashboardComponent,
  },
  {
    path: 'admin-dashboard/getAllBills',
    component: AdminGetAllBillsFormComponent,
  },
  {
    path: 'admin-dashboard/getBillsByYear',
    component: AdminGetBillsByYearFormComponent,
  },
  {
    path: 'admin-dashboard/getBillsByMonth',
    component: AdminGetBillsByMonthFormComponent,
  },
  {
    path: 'admin-dashboard/getBillsByArea',
    component: AdminGetBillsByAreaFormComponent,
  },
  {
    path: 'admin-dashboard/getBillsByCity',
    component: AdminGetBillsByCityFormComponent,
  },
  {
    path: 'admin-dashboard/addUnitsConsumed',
    component: AdminAddUnitsFormComponent,
  },
  { path: 'consumer-dashboard', component: ConsumerDashboardComponent },
  {
    path: 'consumer-dashboard/getAllBills',
    component: ConsumerGetAllBillsFormComponent,
  },
  {
    path: 'consumer-dashboard/getBillsByYear',
    component: ConsumerGetBillsByYearFormComponent,
  },
  {
    path: 'consumer-dashboard/getBillsByMonth',
    component: ConsumerGetBillsByMonthFormComponent,
  },
  {
    path: 'consumer-dashboard/registerConsumer',
    component: ConsumerRegistrationFormComponent,
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
