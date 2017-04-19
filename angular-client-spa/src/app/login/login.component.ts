import { Component } from '@angular/core';
import { Router} from '@angular/router';

import { LoginService } from '../services/login.service';

@Component({
    moduleId: module.id,
    templateUrl: 'login.component.html',
    styleUrls: ['../../assets/css/login.css']
})

export class LoginComponent {
    loading = false;

    constructor(
        private loginService: LoginService,
        private router: Router
        ) { }

    logIn(event: any, username: string, password: string) {
        event.preventDefault();

        this.loginService.logIn(username, password).subscribe(
            user => {
                console.log(user.email);
                if (user.roles[0] === 'ROLE_ADMIN') {
                    this.router.navigate(['/admin']);
                } else {
                    this.router.navigate(['/user']);
                }
        },
            error => alert('Invalid username or password')
        );
    }
}
