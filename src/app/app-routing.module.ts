import { CreateEventComponent } from './create-event/create-event.component';
import { CustomerDetailsComponent } from './customer-details/customer-details.component';
import { CustomerComponent } from './customer/customer.component';
import { EventComponent } from './event/event.component';
import { HomeComponent } from './home/home.component';
import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';



const routes: Routes = [
  { path: '', component: HomeComponent },
  { path: 'customer', component: CustomerComponent },
  { path: 'details/:customerId', component: CustomerDetailsComponent},
  { path: 'create/:customerId', component: CreateEventComponent}

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
