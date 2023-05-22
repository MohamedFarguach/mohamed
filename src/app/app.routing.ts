import { NgModule } from '@angular/core';
import { CommonModule, } from '@angular/common';
import { BrowserModule  } from '@angular/platform-browser';
import { Routes, RouterModule } from '@angular/router';
import { AdminLayoutComponent } from './layouts/admin-layout/admin-layout.component';
import { AuthGuard } from './AuthRout/auth-guard';
import { HomeComponent } from './Livraison/home.component';
import { LoginComponent } from './login/login.component';


const routes: Routes =[
  {
    path: 'login',
    redirectTo: 'login',
    pathMatch: 'full',
  }, {
   path: '',
    component: AdminLayoutComponent,canActivate: [AuthGuard],
    children: [
        {
      path: '',
      loadChildren: () => import('./layouts/admin-layout/admin-layout.module').then(x => x.AdminLayoutModule)
  }]},
  {
    path: '**',
    redirectTo: 'Livraisons'

  }
  ,{
     path: 'login', component: LoginComponent},{
      path: 'Livraisons', component: HomeComponent,canActivate: [AuthGuard]},
];

@NgModule({
  imports: [
    CommonModule,
    BrowserModule,
    RouterModule.forRoot(routes,{
       useHash: false
    })
  ],
  exports: [
  ],
})
export class AppRoutingModule { }
