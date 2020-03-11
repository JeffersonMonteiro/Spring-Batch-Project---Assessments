import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { UserComponent }      from './user/user.component';
import { DashboardComponent }   from './dashboard/dashboard.component';
import { UserDetailComponent }  from './user-detail/user-detail.component';
import { ProductComponent } from './product/product.component';
import { HttpClientModule } from '@angular/common/http';

const routes: Routes = [
  { path: '', redirectTo: '/dashboard', pathMatch: 'full' },
  { path: 'product/:idUser', component: ProductComponent },
  { path: 'dashboard', component: DashboardComponent },
  { path: 'detail/:idUser', component: UserDetailComponent },
  { path: 'user', component: UserComponent },
  
];
@NgModule({
  imports: [ RouterModule.forRoot(routes) ],
  exports: [ RouterModule,
              HttpClientModule ]
})
export class AppRoutingModule { }
