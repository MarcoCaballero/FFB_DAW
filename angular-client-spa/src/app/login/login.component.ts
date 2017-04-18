import { Component } from '@angular/core';
import { Params, Router} from '@angular/router';

import { LoginService } from './login.service';

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
            user => console.log(user.email),
            error => alert('Invalid username or password')
        );
        this.router.navigate(['/user']);
    }

    logOut(event: any) {
        event.preventDefault();

        this.loginService.logOut().subscribe(
            response => {},
            error => console.log('Error when trying to log out: ' + error)
        );
        this.router.navigate(['/user']);
    }
}
