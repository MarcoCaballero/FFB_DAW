import { Injectable } from '@angular/core';
import { Http, Headers, RequestOptions } from '@angular/http';

import 'rxjs/add/operator/toPromise';

import { Team } from '../model/team.model';

import { AuthService } from './auth.service';

@Injectable()
export class TeamService {

    constructor(
        private http: Http,
        private authService: AuthService) { }

    // get sports teams
    getSportsTeams(): Promise<Team[]> {
        return this.http.get('http://127.0.0.1:8080/api/teams/sports')
            .toPromise()
            .then(
            response => response.json())
            .catch(error => console.error(error));
    }

    // get egames teams
    getEgamesTeams(): Promise<Team[]> {
        return this.http.get('http://127.0.0.1:8080/api/teams/egames')
            .toPromise()
            .then(response => response.json())
            .catch(error => console.error(error));
    }

    // create sports team
    newSportsTeam(team: Team): Promise<Team> {
        const headers = new Headers({
            'Authorization': 'Basic ' + this.authService.getCredentials(),
            'Content-Type': 'application/json'
        });
        const options = new RequestOptions({ headers });
        return this.http.post('http://127.0.0.1:8080/api/teams/sports', JSON.stringify(team), options)
            .toPromise()
            .then(response => response.json())
            .catch(error => console.error(error));
    }

    // create egames team
    newEgamesTeam(team: Team): Promise<Team> {
        const headers = new Headers({
            'Authorization': 'Basic ' + this.authService.getCredentials(),
            'Content-Type': 'application/json'
        });
        const options = new RequestOptions({ headers });
        return this.http.post('http://127.0.0.1:8080/api/teams/egames', JSON.stringify(team), options)
            .toPromise()
            .then(response => response.json())
            .catch(error => console.error(error));
    }

    // update sports team
    updateSportsTeam(team: Team): Promise<Team> {
        const headers = new Headers({
            'Authorization': 'Basic ' + this.authService.getCredentials(),
            'Content-Type': 'application/json'
        });
        const options = new RequestOptions({ headers });
        return this.http.put('http://127.0.0.1:8080/api/teams/sports/' + team.id, JSON.stringify(team), options)
            .toPromise()
            .then(response => response.json())
            .catch(error => console.error(error));
    }

    // update egames team
    updateEgamesTeam(team: Team): Promise<Team> {
        const headers = new Headers({
            'Authorization': 'Basic ' + this.authService.getCredentials(),
            'Content-Type': 'application/json'
        });
        const options = new RequestOptions({ headers });
        return this.http.put('http://127.0.0.1:8080/api/teams/egames/' + team.id, JSON.stringify(team), options)
            .toPromise()
            .then(response => response.json())
            .catch(error => console.error(error));
    }

    // remove a team
    deleteTeam(id: number): Promise<any> {
        const headers = new Headers({
            'Authorization': 'Basic ' + this.authService.getCredentials()
        });
        const options = new RequestOptions({ headers });
        return this.http.delete('http://127.0.0.1:8080/api/teams/' + id, options)
            .toPromise()
            .then(undefined)
            .catch(error => console.error(error));
    }
}
