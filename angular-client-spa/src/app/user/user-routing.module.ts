import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';


import { UserComponent } from './user.component';
import { HomeComponent } from './content/home.component';
import { SportsbetComponent } from './content/sportsbet.component';
import { EgamesbetComponent } from './content/egamesbet.component';
import { MyAccountComponent } from './content/myaccount.component';
import { SignupComponent } from './content/signup.component';
import { WithdrawComponent } from './content/withdraw.component';
import { AddcreditComponent } from './content/addcredit.component';
import { PrivacypolicyComponent } from './content/privacypolicy.component';

const routes: Routes = [
  {
    path: 'user',
    component: UserComponent,
    children: [
      {
        path: '',
        component: HomeComponent
      },
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
      },
      {
        path: 'signup',
        component: SignupComponent
      },
      {
        path: 'withdrawcredit',
        component: WithdrawComponent
      },
      {
        path: 'addcredit',
        component: AddcreditComponent
      },
      {
        path: 'policy-privacy',
        component: PrivacypolicyComponent
      }
    ]
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class UserRoutingModule { }

export const routedComponents = [
  HomeComponent,
  SportsbetComponent,
  EgamesbetComponent,
  MyAccountComponent,
  SignupComponent,
  WithdrawComponent,
  AddcreditComponent,
  PrivacypolicyComponent
];
