import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { LoginComponent } from './login.component';
import { AdminComponent } from '../admin/admin.component';
import { UserComponent } from '../user/user.component';

const routes: Routes = [
    { path: 'login', component: LoginComponent, pathMatch: 'full' },
    { path: 'admin', component: AdminComponent, pathMatch: 'full' },
    { path: 'user', component: UserComponent, pathMatch: 'full' }
];

@NgModule({
    imports: [RouterModule.forRoot(routes)],
    exports: [RouterModule],
})

export class AdminRoutingModule { }
