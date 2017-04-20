import { Injectable, OnDestroy } from '@angular/core';
import { Http, Headers, RequestOptions } from '@angular/http';
import { Observable } from 'rxjs/Observable';
import 'rxjs/Rx';


import { User } from '../model/user.model';

@Injectable()
export class AuthService {

    user: User;
    credentials: string;


    buildCredentials(username: String, password: String) {
        this.credentials = utf8_to_b64(username + ':' + password);
        localStorage.setItem('credentials', this.credentials);
    }

    buildUser(user: User) {
        this.user = user;
        localStorage.setItem('user', JSON.stringify(this.user));

    }

    getCredentials() {
        return this.credentials;
    }

    getUser() {
        return this.user;
    }

    isAdmin() {

        if (this.isLogged)
            this.user.roles.indexOf('ROLE_ADMIN') !== -1;
        return false
    }


    isLogged() {

        if (this.user !== null)
            return true
        return false

    }

    reloadAuth() {

        this.setUser(JSON.parse(localStorage.getItem('user')));

        this.setCredentials(localStorage.getItem('credentials'));
    }

    setCredentials(credentials: string) {
        this.credentials = credentials;
    }

    setUser(user: User) {
        this.user = user;
    }







}

/* Function util */
function utf8_to_b64(str) {
    return btoa(encodeURIComponent(str).replace(/%([0-9A-F]{2})/g, function (match, p1) {
        return String.fromCharCode(<any>'0x' + p1);
    }));
}