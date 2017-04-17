import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';


import { HomeComponent } from './content/home.component';
import { SportsbetComponent } from './content/sportsbet.component';
import { EgamesbetComponent } from './content/egamesbet.component';
import { MyAccountComponent } from './content/myaccount.component';

const routes: Routes = [
  {
    path: 'user',
    children: [
      {
        path: 'home',
        component: HomeComponent
      },
      {
        path: 'sportsbet',
        component: SportsbetComponent
      },
      {
        path: 'egamesbet',
        component: EgamesbetComponent
      },
      {
        path: 'myaccount',
        component: MyAccountComponent
      }
    ]
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class UserRoutingModule { }

export const routedComponents = [HomeComponent, SportsbetComponent, EgamesbetComponent, MyAccountComponent];
