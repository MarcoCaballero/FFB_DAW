import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { LoginComponent } from './login/login.component';
import { UserComponent } from './user/user.component';
import { AdminModule } from './admin/admin.module';

const routes: Routes = [
    { path: 'login', component: LoginComponent, pathMatch: 'full' },
    { path: 'user', redirectTo: 'user', pathMatch: 'full' },
    { path: 'admin', component: AdminModule, pathMatch: 'full' },
    { path: '**', redirectTo: '/user/home', pathMatch: 'full' }
];

@NgModule({
    imports: [RouterModule.forRoot(routes)],
    exports: [RouterModule],
})
export class AppRoutingModule { }
