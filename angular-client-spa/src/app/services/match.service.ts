import { Injectable } from '@angular/core';
import { Http, RequestOptions, Headers } from '@angular/http';

import 'rxjs/add/operator/toPromise';

import { matchSportUrl, matchEgamesUrl, matchFootballUrl, matchBasketlUrl ,matchLolUrl , matchCsUrl, matchUrl } from '../paths';

import { SportMatch } from '../model/sport-match.model';

import { AuthService } from './auth.service';
import { EgamesMatch } from '../model/egames-match.model';

@Injectable()
export class MatchService {

    constructor(
        private http: Http,
        private authService: AuthService
    ) { }

    getFootballMatches(): Promise<SportMatch[]> {
        return this.http.get(matchFootballUrl)
            .toPromise()
            .then(
            response => {
                return response.json();
            })
            .catch(error => console.error(error));

    }

    getBasketballMatches(): Promise<SportMatch[]> {
        return this.http.get(matchBasketlUrl)
            .toPromise()
            .then(
            response => {
                return response.json();
            })
            .catch(error => console.error(error));

    }

    getLolMatches(): Promise<EgamesMatch[]> {
        return this.http.get(matchLolUrl)
            .toPromise()
            .then(
            response => {
                return response.json();
            })
            .catch(error => console.error(error));

    }

    getCsMatches(): Promise<EgamesMatch[]> {
        return this.http.get(matchCsUrl)
            .toPromise()
            .then(
            response => {
                return response.json();
            })
            .catch(error => console.error(error));

    }

    newSportMatch(match: SportMatch): Promise<SportMatch> {
        const headers = new Headers({
            'Authorization': 'Basic ' + this.authService.getCredentials(),
            'Content-Type': 'application/json'
        });
        const options = new RequestOptions({ headers });
        return this.http.post(matchSportUrl, JSON.stringify(match), options)
            .toPromise()
            .then(response => response.json())
            .catch(error => console.error(error));
    }

    newEgamesMatch(match: EgamesMatch): Promise<EgamesMatch> {
        const headers = new Headers({
            'Authorization': 'Basic ' + this.authService.getCredentials(),
            'Content-Type': 'application/json'
        });
        const options = new RequestOptions({ headers });
        return this.http.post(matchEgamesUrl, JSON.stringify(match), options)
            .toPromise()
            .then(response => response.json())
            .catch(error => console.error(error));
    }

    updateSportMatchResult(matchFootBallResult: SportMatch): Promise<SportMatch> {
        const headers = new Headers({
            'Authorization': 'Basic ' + this.authService.getCredentials(),
            'Content-Type': 'application/json'
        });
        const options = new RequestOptions({ headers });
        return this.http.put(matchSportUrl + matchFootBallResult.id,
            JSON.stringify(matchFootBallResult), options)
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
        return this.http.put(matchEgamesUrl + matchEgamesResult.id, JSON.stringify(matchEgamesResult), options)
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
        return this.http.delete(matchUrl + id, options)
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
        return this.http.delete(matchUrl + id, options)
            .toPromise()
            .then(undefined)
            .catch(error => console.error(error));
    }


}
