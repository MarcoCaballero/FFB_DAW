import { Injectable } from '@angular/core';
import { Headers, Http, RequestOptions } from '@angular/http';
import { Observable } from 'rxjs/Observable';
import 'rxjs/Rx';

import { AuthService } from './auth.service';

import { User } from '../model/user.model';

@Injectable()
export class UserService {

    user: User;
    // users: User[];

    constructor(private http: Http, private authService: AuthService) { }


    getUser(id: number) {
        const headers: Headers = new Headers({
            'Authorization': 'Basic ' + this.authService.getCredentials()
        });
        const options = new RequestOptions({ headers });
        return this.http.get('http://127.0.0.1:8080/api/user/' + id.toString(), options)
            .map(response => {
                this.user = response.json();
                return response.json();
            })
            .catch(error => Observable.throw('Server error'));
    }

}
