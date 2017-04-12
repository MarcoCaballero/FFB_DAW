// Third pary imports
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';

// App imports
import { MenuHeaderComponent } from './menu-header.component';
import { FooterComponent } from './footer.component';
import { HomeComponent } from './home.component';

import { DropdownModule } from 'ngx-dropdown';

@NgModule({
    imports: [
        CommonModule,
        FormsModule,
        DropdownModule,
        RouterModule.forRoot([
            {
                path: 'home',
                component: HomeComponent
            }
        ])
    ],
    exports: [
        MenuHeaderComponent,
        FooterComponent,
        HomeComponent,
        RouterModule,
        DropdownModule
    ],
    declarations: [
        MenuHeaderComponent,
        FooterComponent,
        HomeComponent
    ],
    providers: [],
})
export class UserModule { }
