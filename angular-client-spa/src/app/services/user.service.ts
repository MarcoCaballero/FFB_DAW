import { Injectable } from '@angular/core';
import { Headers, Http, RequestOptions } from '@angular/http';
import 'rxjs/Rx';

import { AuthService } from './auth.service';

import { User } from '../model/user.model';

@Injectable()
export class UserService {

    constructor(private http: Http, private authService: AuthService) { }

    getUsers(): Promise<User[]> {
        return this.http.get('http://127.0.0.1:8080/api/user')
            .toPromise()
            .then(response => response.json())
            .catch(error => console.error(error));
    }

    getUser(id: number): Promise<User> {
        return this.http.get('http://127.0.0.1:8080/api/user' + id)
        .toPromise()
        .then(response => response.json())
        .catch(error => console.error(error));
    }

    newUser(user: User): Promise<User> {
        const headers = new Headers({
            'Authorization': 'Basic ' + this.authService.getCredentials(),
            'Content-Type': 'application/json'
        });
        const options = new RequestOptions({ headers });
        return this.http.post('http://127.0.0.1:8080/api/user', JSON.stringify(user), options)
        .toPromise()
        .then(response => response.json())
        .catch(error => console.error(error));
    }

    updateRoleUser(): Promise<User> {
        const headers = new Headers({
            'Authorization': 'Basic ' + this.authService.getCredentials()
        });
        const options = new RequestOptions({ headers });
        return this.http.put('http://127.0.0.1:8080/api/user/', options)
            .toPromise()
            .then(response => response.json())
            .catch(error => console.error(error));
    }

    updateUser(user: User): Promise<User> {
        const headers = new Headers({
            'Authorization': 'Basic ' + this.authService.getCredentials(),
            'Content-Type': 'application/json'
        });
        const options = new RequestOptions({ headers });

        return this.http.put('http://127.0.0.1:8080/api/user/', JSON.stringify(user), options)
            .toPromise()
            .then(response => response.json())
            .catch(error => console.error(error));
    }

    removeUser(id: number): Promise<any> {
        const headers = new Headers({
            'Authorization': 'Basic ' + this.authService.getCredentials()
        });
        const options = new RequestOptions({ headers });
        console.log(this.authService.getCredentials());
        return this.http.delete('http://127.0.0.1:8080/api/user/' + id, options)
            .toPromise()
            .then(undefined)
            .catch(error => console.error(error));
    }

}
