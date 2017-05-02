import { Injectable } from '@angular/core';
import { Http, Headers, RequestOptions } from '@angular/http';

import 'rxjs/add/operator/toPromise';

import { betUrl, betMatchhUrl } from '../paths';

import { SportMatch } from '../model/sport-match.model';
import { BetTicket } from '../model/bet-ticket.model';
import { BetSportMatch } from '../model/bet-sport-match.model';

import { AuthService } from './auth.service';

@Injectable()
export class BetService {

    constructor(
        private http: Http,
        private authService: AuthService,
    ) { }

    getLocalTicket(type: string): Promise<BetTicket> {
        return this.http.get(betMatchhUrl + type)
            .toPromise()
            .then(response => response.json())
            .catch(error => console.error(error));
    }

    addSporTeam(spMatch: SportMatch, quota: string): Promise<BetTicket> {
        return this.http.patch(betMatchhUrl + spMatch.id + "/sports/" + quota, spMatch)
            .toPromise()
            .then(response => response.json())
            .catch(error => console.error(error));
    }

    deleteSporTeam(id: number): Promise<BetTicket> {
        return this.http.delete(betMatchhUrl + id + "/sports")
            .toPromise()
            .then(response => response.json())
            .catch(error => console.error(error));
    }

    sendBet(amount: number): Promise<BetTicket> {
        const headers = new Headers({
            'Authorization': 'Basic ' + this.authService.getCredentials(),
            'Content-Type': 'application/json'
        });
        const options = new RequestOptions({ headers });
        return this.http.post(betUrl + amount, this.authService.getUser() ,options)
            .toPromise()
            .then(response => response.json())
            .catch(error => console.error(error));
    }

}


