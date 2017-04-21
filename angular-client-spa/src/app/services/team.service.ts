import { Injectable } from '@angular/core';
import { Http, Headers, RequestOptions } from '@angular/http';

import 'rxjs/add/operator/toPromise';

import { Team } from '../model/team.model';

@Injectable()
export class TeamService {

    constructor(private http: Http) { }

    // get Teams
    getSportsTeams(): Promise<Team[]> {
        return this.http.get('http://127.0.0.1:8080/api/teams/sports')
            .toPromise()
            .then(
            response => {
                return response.json();
            })
            .catch(error => console.error(error));
    }
}
