import { Injectable } from '@angular/core';
import { Http, Headers, RequestOptions } from '@angular/http';

import 'rxjs/add/operator/toPromise';

import { teamUrl, teamSportUrl, teamEgamesUrl } from '../paths';

import { Team } from '../model/team.model';

import { AuthService } from './auth.service';

@Injectable()
export class TeamService {

    constructor(
        private http: Http,
        private authService: AuthService) { }

    // get every teams
    getTeams(): Promise<Team[]> {
        return this.http.get(teamUrl)
        .toPromise()
        .then(response => response.json())
        .catch(error => console.error(error));
    }

    // get sports teams
    getSportsTeams(): Promise<Team[]> {
        return this.http.get(teamSportUrl)
            .toPromise()
            .then(response => response.json())
            .catch(error => console.error(error));
    }

    // get egames teams
    getEgamesTeams(): Promise<Team[]> {
        return this.http.get(teamEgamesUrl)
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
        return this.http.post(teamSportUrl, JSON.stringify(team), options)
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
        return this.http.post(teamEgamesUrl, JSON.stringify(team), options)
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
        return this.http.put(teamSportUrl + team.id, JSON.stringify(team), options)
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
        return this.http.put(teamEgamesUrl + team.id, JSON.stringify(team), options)
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
        return this.http.delete(teamUrl + id, options)
            .toPromise()
            .then(undefined)
            .catch(error => console.error(error));
    }
}
