import { Injectable } from '@angular/core';
import { Http, RequestOptions, Response } from '@angular/http';

import 'rxjs/add/operator/toPromise';

import { SportMatch } from '../model/sport-match.model';

@Injectable()
export class ScoreService {

    constructor(private http: Http) {}

    getFootballMatches(): Promise<SportMatch[]>  {
        return this.http.get('http://127.0.0.1:8080/api/matches/sports/football')
            .toPromise()
            .then(
            response => {
                return response.json();
            })
            .catch(error => console.error(error));

    }

    
}
