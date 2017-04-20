import { Injectable } from '@angular/core';
import { Headers, Http, RequestOptions } from '@angular/http';
import { Observable } from 'rxjs/Observable';
import 'rxjs/Rx';

import { User } from '../model/user.model';

@Injectable()
export class UserService {

    user: User;
    users: User[];
    credentials: string;

    constructor(private http: Http) { }

    setHeaders(credentials: string) {
        this.credentials = credentials;
    }

    getUser(id: number) {
        this.credentials = localStorage.getItem('credentials');
        let headers: Headers = new Headers({'Authorization': 'Basic ' + this.credentials});
        let options = new RequestOptions({ headers });
        return this.http.get('http://127.0.0.1:8080/api/user/' + id.toString(), options)
            .map(response => {
                this.user = response.json();
                return response.json();
            })
            .catch(error => Observable.throw('Server error'));
    }

}
