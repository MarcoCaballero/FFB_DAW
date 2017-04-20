import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { AdminHomeComponent } from './content/admin-home.component';
import { AdminTeamsComponent } from './content/admin-teams.component';
import { AdminMatchesComponent } from './content/admin-matches.component';
import { AdminScoresComponent } from './content/admin-scores.component';
import { AdminComponent } from './admin.component';


import { AdminAuthGuard } from '../services/adminAuth.guard';

const routes: Routes = [
    {
        path: 'admin',
        component: AdminComponent,
        canActivate: [AdminAuthGuard],

        children: [
            {
                path: '',
                component: AdminHomeComponent
            },
            {
                path: 'home',
                component: AdminHomeComponent
            },
            {
                path: 'teams',
                component: AdminTeamsComponent
            },
            {
                path: 'matches',
                component: AdminMatchesComponent
            },
            {
                path: 'scores',
                component: AdminScoresComponent
            }
        ]
    },
];

@NgModule({
    imports: [RouterModule.forChild(routes)],
    exports: [RouterModule],
})

export class AdminRoutingModule { }

export const routedComponents = [AdminHomeComponent, AdminTeamsComponent, AdminMatchesComponent, AdminScoresComponent];
