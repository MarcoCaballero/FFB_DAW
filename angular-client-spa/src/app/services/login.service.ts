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

        // Build and store AuthService credentials (field)
        this.authService.buildCredentials(username, password);

        // Create headers to send Basic auth
        const headers = new Headers({
            'Authorization': 'Basic ' + this.authService.getCredentials()
        });

        // Create options header
        const options = new RequestOptions({ headers });


        return this.http.get('http://127.0.0.1:8080/api/logIn', options).map(
            response => {
                this.authService.buildUser(response.json());
                return response;
            })
            .catch(error => Observable.throw('Server error'));
    }

    logOut() {
        const headers = new Headers({
            'Authorization': 'Basic ' + this.authService.getCredentials()
        });

        const options = new RequestOptions({ headers });

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
