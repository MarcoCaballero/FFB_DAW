import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { LoginModule } from './login/login.module';
import { UserModule } from './user/user.module';
import { AdminModule } from './admin/admin.module';


import { AdminAuthGuard } from './services/adminAuth.guard';
import { UserAuthGuard } from './services/userAuth.guard';



const routes: Routes = [
    { path: 'login', component: LoginModule, pathMatch: 'full' },
    { path: 'user', component: UserModule},
    { path: 'admin', component: AdminModule, canActivate: [AdminAuthGuard] },
    { path: '**', redirectTo: '/user/home', pathMatch: 'full' }
];

@NgModule({
    imports: [RouterModule.forRoot(routes)],
    exports: [RouterModule],
})
export class AppRoutingModule { }
