import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpClient, HttpClientModule } from '@angular/common/http';
import {ReactiveFormsModule} from '@angular/forms';

import { AppComponent } from './app.component';
import { VolunteersComponent } from './volunteers/volunteers.component';
import { VolunteerDetailComponent } from './volunteer-detail/volunteer-detail.component';
import { MessagesComponent } from './messages/messages.component';
import { AppRoutingModule } from './app-routing.module';
import { DashboardComponent } from './dashboard/dashboard.component';
import { CreateVolunteerComponent } from './create-volunteer/create-volunteer.component';

@NgModule({
  declarations: [
    AppComponent,
    VolunteersComponent,
    VolunteerDetailComponent,
    MessagesComponent,
    DashboardComponent,
    CreateVolunteerComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    AppRoutingModule,
    HttpClientModule,
    ReactiveFormsModule
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
