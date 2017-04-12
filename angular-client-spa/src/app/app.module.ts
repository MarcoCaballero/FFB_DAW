import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpModule } from '@angular/http';
import {DropdownModule} from "ngx-dropdown";

import { AppComponent } from './app.component';
import { UserMenuHeaderComponent } from './user-menu-header.component';
import { UserFooterComponent } from './user-footer.component';

@NgModule({
  declarations: [
    AppComponent,
    UserMenuHeaderComponent,
    UserFooterComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpModule,
    DropdownModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
