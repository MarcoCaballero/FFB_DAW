import { Injectable, OnDestroy } from '@angular/core';
import { Http, Headers, RequestOptions } from '@angular/http';
import { Observable } from 'rxjs/Observable';
import 'rxjs/Rx';

import { loginUrl, logoutUrl } from '../paths';

import { User } from '../model/user.model';

import { AuthService } from './auth.service';

@Injectable()
export class LoginService implements OnDestroy {

    ngOnDestroy() {
        localStorage.clear(); // Clear locarStorage onDestroy
    }

    constructor(private http: Http, private authService: AuthService) { }

    logIn(username: string, password: string) {


        this.authService.buildCredentials(username, password);   // Build and store AuthService credentials (field)


        const headers = new Headers({
            'Authorization': 'Basic ' + this.authService.getCredentials()   // Create headers to send Basic auth
        });


        const options = new RequestOptions({ headers });  // Create options header


        return this.http.get(loginUrl, options).map(
            response => {
                this.authService.buildUser(response.json());  // Create a persistent user by Auth.service
                return response;
            })
            .catch(error => Observable.throw('Server error'));
    }

    logOut() {
        const headers = new Headers({
            'Authorization': 'Basic ' + this.authService.getCredentials() // Get persistent user credentials
        });

        const options = new RequestOptions({ headers }); // Create options header

        return this.http.get(logoutUrl, options).map(
            response => {
                // Important!
                localStorage.clear(); // Clear localstorage
                this.authService.clear(); // Clear authService
                return response;
            }
        );
    }

}

function utf8_to_b64(str) {
    return btoa(encodeURIComponent(str).replace(/%([0-9A-F]{2})/g, function (match, p1) {
        return String.fromCharCode(<any>'0x' + p1);
    }));
}
