// Third party imports
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { AccordionModule } from 'ngx-accordion';

// App import
import { AdminHeaderComponent } from './shared/admin-header.component';
import { AdminFooterComponent } from './shared/admin-footer.component';
import { AdminComponent } from './admin.component';
import { AdminRoutingModule, routedComponents } from './admin-routing.module';

@NgModule({
    imports: [
        CommonModule,
        FormsModule,
        AccordionModule,
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
    providers: [],
})

export class AdminModule { }
