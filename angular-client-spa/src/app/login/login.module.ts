// Third party imports
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

// Imports for loading & configuring the in-memory web api
import { InMemoryWebApiModule } from 'angular-in-memory-web-api';

// App import
import { LoginComponent } from './login.component';
import { LoginRoutingModule } from './login-routing.module';

import { LoginService } from '../services/login.service';

@NgModule({
    imports: [
        CommonModule,
        FormsModule,
        LoginRoutingModule
    ],
    exports: [
        LoginComponent,
        LoginRoutingModule,
    ],
    declarations: [
        LoginComponent
    ],
    providers: [
        LoginService
    ],
})

export class LoginModule { }
