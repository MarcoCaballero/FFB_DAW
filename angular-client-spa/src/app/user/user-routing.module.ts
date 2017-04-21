import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';


import { UserComponent } from './user.component';
import { HomeComponent } from './content/home.component';
import { SportsbetComponent } from './content/bet/sportsbet.component';
import { EgamesbetComponent } from './content/bet/egamesbet.component';
import { WithdrawComponent } from './content/myaccount/withdraw.component';
import { AddcreditComponent } from './content/myaccount/addcredit.component';
import { MyAccountComponent } from './content/myaccount/myaccount.component';
import { SignupComponent } from './content/signup/signup.component';
import { PrivacypolicyComponent } from './content/policy/privacypolicy.component';


import { UserAuthGuard } from '../core/userAuth.guard';

const routes: Routes = [
  {
    path: 'user',
    component: UserComponent,
    children: [
      {
        path: '',
        redirectTo: '/user/home',
        pathMatch: 'full'
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
        component: MyAccountComponent,
        canActivate: [UserAuthGuard]
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
