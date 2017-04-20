import { Injectable, OnDestroy } from '@angular/core';
import { Http, Headers, RequestOptions } from '@angular/http';
import { Observable } from 'rxjs/Observable';
import 'rxjs/Rx';

import { User } from '../model/user.model';

import { UserService } from './user.service';

@Injectable()
export class LoginService implements OnDestroy {

    isLogged = false;
    isAdmin = false;
    user: User;
    credentials: string;

    ngOnDestroy() {
        console.log('localStorage called from ngOnDestroy');
        localStorage.clear();
    }

    constructor(
        private http: Http,
        private userService: UserService) { }

    logIn(username: string, password: string) {
        this.credentials = utf8_to_b64(username + ':' + password);
        let headers = new Headers({
            'Authorization': 'Basic ' + this.credentials
        });
        let options = new RequestOptions({ headers });
        return this.http.get('http://127.0.0.1:8080/api/logIn', options).map(
            response => {
                let id = response.json().id;
                localStorage.setItem('credentials', this.credentials);
                localStorage.setItem('id', String(id));
                this.userService.setHeaders(this.credentials);
                this.user = response.json();
                this.isAdmin = this.user.roles.indexOf('ROLE_ADMIN') !== -1;
                localStorage.setItem('user', username);
                localStorage.setItem('password', password);
                this.isLogged = true;
                return this.user;
            })
            .catch(error => Observable.throw('Server error'));
    }

    logOut() {
        let headers = new Headers({
            'Authorization': 'Basic ' + this.credentials
        });
        let options = new RequestOptions({ headers });
        return this.http.get('http://127.0.0.1:8080/api/logOut', options).map(
            response => {
                localStorage.clear();
                this.isLogged = false;
                this.isAdmin = false;
                return response;
            }
        );
    }

    checkCredentials() {
        return (localStorage.getItem('user') !== null);
    }
}

function utf8_to_b64(str) {
    return btoa(encodeURIComponent(str).replace(/%([0-9A-F]{2})/g, function (match, p1) {
        return String.fromCharCode(<any>'0x' + p1);
    }));
}
