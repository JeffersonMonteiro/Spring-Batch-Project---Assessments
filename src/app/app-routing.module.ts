import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { VolunteersComponent } from './volunteers/volunteers.component';
import { DashboardComponent } from './dashboard/dashboard.component';
import { VolunteerDetailComponent } from './volunteer-detail/volunteer-detail.component';


const routes: Routes = [
  { path: '', redirectTo: '/dashboard', pathMatch: 'full' },
  { path: 'volunteers', component: VolunteersComponent },
  { path: 'dashboard', component: DashboardComponent },
  { path: 'detail/:id', component: VolunteerDetailComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
