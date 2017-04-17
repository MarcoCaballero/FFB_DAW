import { Injectable } from '@angular/core';
import { Http, Headers } from '@angular/http';

import 'rxjs/add/operator/toPromise';

import { User } from './user';

@Injectable()
export class LoginService {
    private loginUrl = 'api/login';

    constructor(private http: Http) { }

    private handleError(error: any): Promise<any> {
        console.error('An error occurred', error); // for demo purposes only
        return Promise.reject(error.message || error);
    }

    login(username: string, password: string): Promise<User> {
        return this.http.get(this.loginUrl, JSON.stringify({username: username, password: password}))
        .toPromise()
        .then(response => response.json().data as User)
        .catch(this.handleError);
    }

    logout() {
        localStorage.removeItem('currentUser');
    }
}
