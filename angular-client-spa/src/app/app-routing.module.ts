import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

const routes: Routes = [
    { path: 'user', redirectTo: '/user/home', pathMatch: 'full' },
    { path: '', redirectTo: '/user/home', pathMatch: 'full' },
    { path: 'admin', redirectTo: '/admin', pathMatch: 'full' }
];

@NgModule({
    imports: [RouterModule.forRoot(routes)],
    exports: [RouterModule],
})
export class AppRoutingModule { }
