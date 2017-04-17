import { Component, OnInit } from '@angular/core';

import { LoginService } from './login.service';

import { User } from './user';

@Component({
    moduleId: module.id,
    templateUrl: 'login.component.html',
    styleUrls: ['../../assets/css/login.css']
})

export class LoginComponent implements OnInit {
    users: User;
    loading = false;
    isOk = false;
    username = '';
    password = '';

    constructor(
        private loginService: LoginService
    ) { }

    ngOnInit() {
        this.loading = false;
        this.isOk = false;
    }

    login() {
        this.loading = true;
        this.loginService.login(this.username, this.password).then(users => this.users = users);
        if (!this.login == null) {
            this.isOk = true;
        }
    }
}
