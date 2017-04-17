// Third party imports
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

// Imports for loading & configuring the in-memory web api
import { InMemoryWebApiModule } from 'angular-in-memory-web-api';
import { InMemoryDataService } from './in-memory-data.service';

// App import
import { LoginComponent } from './login.component';
import { AdminRoutingModule } from './login-routing.module';

import { LoginService } from './login.service';

@NgModule({
    imports: [
        CommonModule,
        FormsModule,
        AdminRoutingModule
    ],
    exports: [
        LoginComponent,
        AdminRoutingModule,
    ],
    declarations: [
        LoginComponent
    ],
    providers: [
        LoginService
    ],
})

export class LoginModule { }
