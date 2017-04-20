import { Injectable, OnDestroy } from '@angular/core';
import { Http, Headers, RequestOptions } from '@angular/http';
import { Observable } from 'rxjs/Observable';
import 'rxjs/Rx';

import { loginUrl } from '../paths';

import { User } from '../model/user.model';

import { AuthService } from './auth.service';

@Injectable()
export class LoginService implements OnDestroy {

    ngOnDestroy() {
        console.log('localStorage called from ngOnDestroy');
        localStorage.clear();
    }

    constructor(private http: Http, private authService: AuthService) {

    }

    logIn(username: string, password: string) {

        console.log('login.service.ts START!');

        // Build and store AuthService credentials (field)
        this.authService.buildCredentials(username, password);

        // Create headers to send Basic auth
        let headers = new Headers({
            'Authorization': 'Basic ' + this.authService.getCredentials()
        });

        // Create options header
        let options = new RequestOptions({ headers });


        return this.http.get('http://127.0.0.1:8080/api/logIn', options).map(
            response => {
                // let id = response.json().id;
                // localStorage.setItem('id', String(id));
                // let user = response.json();
                console.log('login.service.ts :42' + response.json());
                // this.authService.buildUser(user);
                // console.log('login.service.ts :44' + this.authService.getUser());
                this.authService.buildUser(response.json());
                return response;

            })
            .catch(error => Observable.throw('Server error'));
    }

    logOut() {
        let headers = new Headers({
            'Authorization': 'Basic ' + this.authService.getCredentials()
        });
        let options = new RequestOptions({ headers });
        return this.http.get('http://127.0.0.1:8080/api/logOut', options).map(
            response => {
                // Important!
                localStorage.clear();
                this.authService.setCredentials(null);
                this.authService.setUser(null);
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
