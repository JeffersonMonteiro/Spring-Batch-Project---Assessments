import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { PersonComponent } from './person/person.component';

import { FormsModule } from '@angular/forms';
import { PersonDetailComponent } from './person-detail/person-detail.component';
import { MessagesComponent } from './messages/messages.component';
import { DashboardComponent } from './dashboard/dashboard.component'; // <-- NgModel lives here

import { HttpClientModule } from '@angular/common/http';
import { PersonSearchComponent } from './person-search/person-search.component';

@NgModule({
  declarations: [
    AppComponent,
    PersonComponent,
    PersonDetailComponent,
    MessagesComponent,
    DashboardComponent,
    PersonSearchComponent
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
