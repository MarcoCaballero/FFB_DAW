// Third party imports
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { DropdownModule } from 'ngx-dropdown';
import { CarouselModule, TabsModule, CollapseModule } from 'ngx-bootstrap';

// App imports
import { MenuHeaderComponent } from './shared/menu-header.component';
import { FooterComponent } from './shared/footer.component';
import { UserComponent } from './user.component';
import { UserRoutingModule } from './user-routing.module';
import { routedComponents } from './user-routing.module';

import { LoginService } from '../services/login.service';

@NgModule({
    imports: [
        CommonModule,
        FormsModule,
        DropdownModule,
        CarouselModule.forRoot(),
        TabsModule.forRoot(),
        CollapseModule.forRoot(),
        UserRoutingModule
    ],
    exports: [
        UserComponent,
        routedComponents,
        UserRoutingModule
    ],
    declarations: [
        UserComponent,
        MenuHeaderComponent,
        FooterComponent,
        routedComponents
    ],
    providers: [
        LoginService
    ],
})
export class UserModule { }
