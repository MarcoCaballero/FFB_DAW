import { Injectable } from '@angular/core';
import { Http,  RequestOptions } from '@angular/http';

import { SportMatch } from '../model/sport-match.model';

@Injectable()
export class ScoreService {

    private matchFootballResults: SportMatch[] = [];

    constructor(private http: Http) {}

    getFootballMatches(){
        
        this.http.get("http://127.0.0.1:8080/api/matches/sports").subscribe(
            response => //response => this.extractFootballMatch(response)
            {
                let matches = response.json();
                for(let i of matches){
                    if((i.isFinished) && (i.type === "Football")){
                        this.matchFootballResults.push(i);
                    }
                }
            },
            error => {
                console.error(error);
            }
        );
        return this.matchFootballResults;
    }

    /*private extractFootballMatch(response: Response){
        let matches: SportMatch[] = [];
        matches = response.json();
        for(let i of matches){
            if((i.isFinished) && (i.type === "Football")){
                this.matchFootballResults.push(i);
            }
        }
        return this.matchFootballResults;
    }*/
}
