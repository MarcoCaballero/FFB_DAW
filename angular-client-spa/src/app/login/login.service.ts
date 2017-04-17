import { Injectable } from '@angular/core';
import { Http } from '@angular/http';

@Injectable()
export class AuthService {
    constructor(private http: Http) { }

    login(username: string, password: string) {
        this.http.post('/api/login', JSON.stringify({ username: username, password: password})).subscribe(
            response => {
                let user = response.json();
                if (user && user.token) {
                    // store user details and JWT token in local storage
                    // to keep user logged in between page refreshes
                    localStorage.setItem('currentUser', JSON.stringify(user));
                }
            },
            error => console.error(error)
        );
    }

    logout() {
        localStorage.removeItem('currentUser');
    }
}
