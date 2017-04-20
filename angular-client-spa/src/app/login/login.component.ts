import { Component } from '@angular/core';
import { Router } from '@angular/router';

import { User } from '../model/user.model';

import { LoginService } from '../services/login.service';
import { AuthService } from '../services/auth.service';

@Component({
    moduleId: module.id,
    templateUrl: 'login.component.html',
    styleUrls: ['../../assets/css/login.css']
})

export class LoginComponent {
    loading = false;
    user: User;


    constructor(
        private loginService: LoginService,
        private authService: AuthService,
        private router: Router
    ) { }

    logIn(event: any, username: string, password: string) {
        event.preventDefault();

        this.loginService.logIn(username, password).subscribe(
            user => {
                this.user = user;
                 console.log('login.component.ts :32' + this.user);
                if (this.authService.isLogged) {
                    if (this.authService.isAdmin) {
                        this.router.navigate(['/admin']);
                    } else {
                        this.router.navigate(['/user']);
                    }
                } else {
                    console.error('Invalid username or password');
                    alert('Invalid username or password');
                }

            },
            error => alert('Invalid username or password')
        );
    }
}
