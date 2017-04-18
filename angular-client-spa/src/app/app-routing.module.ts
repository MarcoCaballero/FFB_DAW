import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { LoginModule } from './login/login.module';
import { UserModule } from './user/user.module';
import { AdminModule } from './admin/admin.module';

const routes: Routes = [
    { path: 'login', component: LoginModule, pathMatch: 'full' },
    { path: 'user', component: UserModule, pathMatch: 'full' },
    { path: 'admin', component: AdminModule, pathMatch: 'full' },
    { path: '**', redirectTo: '/user/home', pathMatch: 'full' }
];

@NgModule({
    imports: [RouterModule.forRoot(routes)],
    exports: [RouterModule],
})
export class AppRoutingModule { }
