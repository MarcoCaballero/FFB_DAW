// Third party imports
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { AccordionModule } from 'ngx-accordion';
import { AlertModule } from 'ngx-bootstrap/alert';

// App import
import { AdminHeaderComponent } from './shared/admin-header.component';
import { AdminFooterComponent } from './shared/admin-footer.component';
import { AdminComponent } from './admin.component';
import { AdminRoutingModule, routedComponents } from './admin-routing.module';

import { LoginService } from '../services/login.service';
import { TeamService } from '../services/team.service';

@NgModule({
    imports: [
        CommonModule,
        FormsModule,
        AccordionModule,
        AlertModule.forRoot(),
        AdminRoutingModule
    ],
    exports: [
        AdminComponent,
        AdminRoutingModule,
        routedComponents
    ],
    declarations: [
        AdminComponent,
        AdminHeaderComponent,
        AdminFooterComponent,
        routedComponents
    ],
    providers: [
        LoginService,
        TeamService
    ],
})

export class AdminModule { }
