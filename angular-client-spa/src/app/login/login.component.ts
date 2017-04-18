import { Component, OnInit } from '@angular/core';

import { LoginService } from './login.service';

import { User } from './user';

@Component({
    moduleId: module.id,
    templateUrl: 'login.component.html',
    styleUrls: ['../../assets/css/login.css']
})

export class LoginComponent implements OnInit {
    user: User;
    loading = false;
    isOk = true;
    username = '';
    password = '';

    constructor(private loginService: LoginService) { }

    ngOnInit() {
        this.loading = false;
        this.isOk = true;
    }
/*
    login() {
        this.loading = true;

        if (this.username === 'peloxo' && this.password === '123') {
            this.isOk = true;
        } else {
            this.isOk = false;
        }
        this.loading = false;
    };*/

    login() {
        this.loading = true;
        this.loginService.login(this.username, this.password).then(user => {
            this.user = user;
            if (this.user != null) {
                this.isOk = true;
            } else {
                this.isOk = false;
            }
        });
    }

}
