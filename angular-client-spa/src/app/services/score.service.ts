import { Injectable } from '@angular/core';
import { Http, RequestOptions, Headers } from '@angular/http';

import 'rxjs/add/operator/toPromise';

import { SportMatch } from '../model/sport-match.model';

import { AuthService } from './auth.service';
import { EgamesMatch } from "app/model/egames-match.model";

@Injectable()
export class ScoreService {

    constructor(
        private http: Http,
        private authService: AuthService
    ) { }

    getFootballMatches(): Promise<SportMatch[]> {
        return this.http.get('http://127.0.0.1:8080/api/matches/sports/football')
            .toPromise()
            .then(
            response => {
                return response.json();
            })
            .catch(error => console.error(error));

    }

    getBasketballMatches(): Promise<SportMatch[]> {
        return this.http.get('http://127.0.0.1:8080/api/matches/sports/basketball')
            .toPromise()
            .then(
            response => {
                
                return response.json();
            })
            .catch(error => console.error(error));

    }

    getLolMatches(): Promise<EgamesMatch[]> {
        return this.http.get('http://127.0.0.1:8080/api/matches/egames/lol')
            .toPromise()
            .then(
            response => {
                return response.json();
            })
            .catch(error => console.error(error));

    }

    getCsMatches(): Promise<EgamesMatch[]> {
        return this.http.get('http://127.0.0.1:8080/api/matches/egames/cs')
            .toPromise()
            .then(
            response => {
                return response.json();
            })
            .catch(error => console.error(error));

    }

    updateSportMatchResult(matchFootBallResult: SportMatch): Promise<SportMatch> {
        const headers = new Headers({
            'Authorization': 'Basic ' + this.authService.getCredentials(),
            'Content-Type': 'application/json'
        });
        const options = new RequestOptions({ headers });
        return this.http.put('http://127.0.0.1:8080/api/matches/sports/' + matchFootBallResult.id, JSON.stringify(matchFootBallResult), options)
            .toPromise()
            .then(response => response.json())
            .catch(error => console.error(error));
    }

    updateEgamesMatchResult(matchEgamesResult: EgamesMatch): Promise<EgamesMatch> {
        const headers = new Headers({
            'Authorization': 'Basic ' + this.authService.getCredentials(),
            'Content-Type': 'application/json'
        });
        const options = new RequestOptions({ headers });
        console.log(JSON.stringify(matchEgamesResult));
        return this.http.put('http://127.0.0.1:8080/api/matches/egames/' + matchEgamesResult.id, JSON.stringify(matchEgamesResult), options)
            .toPromise()
            .then(response => response.json())
            .catch(error => console.error(error));
    }

    removeSportMatchResult(id: number): Promise<any> {
        const headers = new Headers({
            'Authorization': 'Basic ' + this.authService.getCredentials()
        });
        const options = new RequestOptions({ headers });
        console.log(this.authService.getCredentials());
        return this.http.delete('http://127.0.0.1:8080/api/matches/' + id, options)
            .toPromise()
            .then(undefined)
            .catch(error => console.error(error));
    }

    removeEgamesMatchResult(id: number): Promise<any> {
        const headers = new Headers({
            'Authorization': 'Basic ' + this.authService.getCredentials()
        });
        const options = new RequestOptions({ headers });
        console.log(this.authService.getCredentials());
        return this.http.delete('http://127.0.0.1:8080/api/matches/' + id, options)
            .toPromise()
            .then(undefined)
            .catch(error => console.error(error));
    }


}
