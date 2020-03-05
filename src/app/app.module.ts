import { HttpClientModule, HttpClient } from '@angular/common/http';
import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HomeComponent } from './home/home.component';
import { EventComponent } from './event/event.component';
import { FormsModule } from '@angular/forms';
import { CreateCustomerComponent } from './create-customer/create-customer.component';
import { CustomerDetailsComponent } from './customer-details/customer-details.component';
import { CustomerComponent } from './customer/customer.component';
import { CreateEventComponent } from './create-event/create-event.component';
import { EventDetailsComponent } from './event-details/event-details.component';


@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    EventComponent,
    CreateCustomerComponent,
    CustomerDetailsComponent,
    CustomerComponent,
    CreateEventComponent,
    EventDetailsComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
