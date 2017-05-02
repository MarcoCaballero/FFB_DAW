import { Component, OnInit } from '@angular/core';

import { SportMatch } from 'app/model/sport-match.model';
import { BetTicket } from 'app/model/bet-ticket.model';

import { MatchService } from 'app/services/match.service';
import { UserService } from 'app/services/user.service';
import { AuthService } from 'app/services/auth.service';
import { BetService } from 'app/services/bet.service';

@Component({
    moduleId: module.id,
    selector: 'ffbcomp-sportsbet',
    templateUrl: 'sportsbet.component.html',
    styleUrls: ['../../../../assets/css/styles.css']
})

export class SportsbetComponent implements OnInit {

    title = 'Apuestas deportivas';
    private footballMatches: SportMatch[];
    private basketballMatches: SportMatch[];
    private sportTicket: BetTicket;
    private egamesTicket: BetTicket;
    public potentialGainTemporary = 0.00;
    public multiplicator = 1;

    constructor(private matchService: MatchService, private betService: BetService, private userService: UserService
        , private authService: AuthService) { }

    ngOnInit() {
        this.getBasketballMatchesFinished();
        this.getEgamesTicket();
        this.getFootballMatchesFinished();
        this.getSportTicket();
    }

    addSportMatchToBet(sportMatch: SportMatch, quota: string) {
        this.betService.addSporTeam(sportMatch, quota)
            .then(ticket => {
                this.sportTicket = ticket;
                this.potentialGainTemporary = ticket.potentialGain;
            });
    }

    getBasketballMatchesFinished() {
        this.matchService.getBasketballMatches()
            .then(sportMatches => this.basketballMatches = sportMatches.filter(matches => matches.finished === false));
    }

    getFootballMatchesFinished() {
        this.matchService.getFootballMatches()
            .then(sportMatches => this.footballMatches = sportMatches.filter(matches => matches.finished === false));
    }

    getSportTicket() {
        this.betService.getLocalTicket('sports')
            .then(ticket => this.sportTicket = ticket);
    }
    getEgamesTicket() {
        this.betService.getLocalTicket('egames')
            .then(ticket => this.egamesTicket = ticket);
    }

    onChangeAmount(value: number) {
        this.potentialGainTemporary = this.sportTicket.potentialGain * value;
        this.multiplicator = value;
    }

    removeSportMatchToBet(index: number) {
        const confirmationMessage = confirm(`¿Estás seguro de que quieres borrar el partido con id  ${index}  ?`);
        if (confirmationMessage) {
            this.betService.deleteSporTeam(index)
                .then(ticket => {
                    this.sportTicket = ticket;
                    this.potentialGainTemporary = ticket.potentialGain;
                });
        }
    }

    sendBet() {
        const confirmationMessage = confirm(`Se va a proceder a enviar la apuesta, y descontar ${this.multiplicator}€ de su cuenta, ¿ Desea continuar ? `);
        if (confirmationMessage) {
            this.betService.sendBet(this.multiplicator)
                .then(ticket => {
                    this.sportTicket = ticket;
                    this.uploadUser();
                });
        }
    }

    uploadUser() {
        this.userService
            .getUser(this.authService.getUser().id)
            .then(user => {
                this.authService.buildUser(user);
                this.userService.announceChange(user);
            });
    }



}
