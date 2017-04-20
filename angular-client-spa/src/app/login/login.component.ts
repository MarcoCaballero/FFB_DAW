import { Component } from '@angular/core';
import { Router } from '@angular/router';

import { User } from '../model/user.model';

import { LoginService } from '../services/login.service';

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
        private router: Router
    ) { }

    logIn(event: any, username: string, password: string) {
        event.preventDefault();

        this.loginService.logIn(username, password).subscribe(
            user => {
                this.user = user;
                if (this.loginService.isAdmin) {
                    this.router.navigate(['/admin']);
                } else {
                    this.router.navigate(['/user']);
                }
            },
            error => alert('Invalid username or password')
        );
    }
}
