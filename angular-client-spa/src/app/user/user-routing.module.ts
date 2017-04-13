import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';


import { HomeComponent } from './shared/home.component';

const routes: Routes = [
  {
    path: 'user',
    children: [
      {
        path: 'home',
        component: HomeComponent
      }
    ]
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class UserRoutingModule { }

export const routedComponents = [HomeComponent];
