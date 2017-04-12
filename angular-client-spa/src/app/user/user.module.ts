// Third pary imports
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

// App imports
import { MenuHeaderComponent } from './menu-header.component';
import { FooterComponent } from './footer.component';

@NgModule({
    imports: [],
    exports: [
        MenuHeaderComponent,
        FooterComponent
    ],
    declarations: [
        MenuHeaderComponent,
        FooterComponent
    ],
    providers: [],
})
export class UserModule { }
