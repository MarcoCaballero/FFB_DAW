// Third party imports
import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpModule } from '@angular/http';

// App imports
import { AppComponent } from './app.component';

import { AppRoutingModule } from './app-routing.module';
import { UserModule } from './user/user.module';
import { AdminModule } from './admin/admin.module';
import { LoginModule } from './login/login.module';

import { LoginService } from './services/login.service';
import { UserService } from './services/user.service';
import { AuthService } from './services/auth.service';
import { ScoreService } from './services/score.service';
import { AdminAuthGuard } from './core/adminAuth.guard';
import { UserAuthGuard } from './core/userAuth.guard';


@NgModule({
  declarations: [
    AppComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpModule,
    LoginModule,
    UserModule,
    AdminModule,
    AppRoutingModule
  ],
  providers: [
    LoginService,
    UserService,
    ScoreService,
    AuthService,
    AdminAuthGuard,
    UserAuthGuard

    
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }

