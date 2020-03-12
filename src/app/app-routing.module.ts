import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { VolunteersComponent } from './volunteers/volunteers.component';
import { DashboardComponent } from './dashboard/dashboard.component';
import { VolunteerDetailComponent } from './volunteer-detail/volunteer-detail.component';
import { CreateVolunteerComponent } from './create-volunteer/create-volunteer.component';
import { ActivityComponent } from './activity//activity.component';
import { ActivityDetailComponent } from './activity-detail/activity-detail.component';
import { CreateActivityComponent } from './create-activity/create-activity.component';
import { ActivityUpdateComponent } from './activity-update/activity-update.component';


const routes: Routes = [
  { path: '', redirectTo: '/volunteers', pathMatch: 'full' },
  { path: 'volunteers', component: VolunteersComponent },
  { path: 'dashboard', component: DashboardComponent },
  { path: 'detail/:id', component: VolunteerDetailComponent },
  { path: 'create', component: CreateVolunteerComponent },
  { path: 'activity', component: ActivityComponent },
  { path: 'actDetail/:id', component: ActivityDetailComponent },
  { path: 'createAct/:id', component: CreateActivityComponent },
  { path: 'updateAct/:id/:activityId', component: ActivityUpdateComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
