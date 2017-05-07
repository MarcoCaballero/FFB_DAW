import { Component, OnInit } from '@angular/core';

import { EgamesMatch } from 'app/model/egames-match.model';
import { BetTicket } from 'app/model/bet-ticket.model';

import { MatchService } from 'app/services/match.service';
import { UserService } from 'app/services/user.service';
import { AuthService } from 'app/services/auth.service';
import { BetService } from 'app/services/bet.service';

@Component({
    moduleId: module.id,
    selector: 'ffbcomp-egamesbet',
    templateUrl: 'egamesbet.component.html',
    styleUrls: ['../../../../assets/css/styles.css']
})

export class EgamesbetComponent implements OnInit {

    title = 'Apuestas electrónicas';
    public lolMatches: EgamesMatch[];
    public csMatches: EgamesMatch[];
    public egamesTicket: BetTicket;
    public potentialGainTemporary = 0.00;
    public multiplicator = 1;

    constructor(private matchService: MatchService, private betService: BetService, private userService: UserService
        , private authService: AuthService) { }

    ngOnInit() {
        this.getLolEgamesMatches();
        this.getCsEgamesMatches();
        this.getEgamesTicket();
    }

    addEgamesMatchToBet(egamesMatch: EgamesMatch, quota: string) {
        this.betService.addEgamesTeam(egamesMatch, quota)
            .then(ticket => {
                this.egamesTicket = ticket;
                this.potentialGainTemporary = ticket.potentialGain;
            });
    }


    getEgamesTicket() {
        this.betService.getLocalTicket('egames')
            .then(ticket => this.egamesTicket = ticket);
    }

    getLolEgamesMatches() {
        this.matchService.getLolMatches()
            .then(egamesMatches => this.lolMatches = egamesMatches.filter(matches => matches.finished === false));
    }

    getCsEgamesMatches() {
        this.matchService.getCsMatches()
            .then(egamesMatches => this.csMatches = egamesMatches.filter(matches => matches.finished === false));
    }

    onChangeAmount(value: number) {
        this.potentialGainTemporary = this.egamesTicket.potentialGain * value;
        this.multiplicator = value;
    }

    removeEgamesMatchToBet(index: number) {
        const confirmationMessage = confirm(`¿Estás seguro de que quieres borrar el partido con id  ${index}  ?`);
        if (confirmationMessage) {
            this.betService.deleteEgamesTeam(index)
                .then(ticket => {
                    this.egamesTicket = ticket;
                    this.potentialGainTemporary = ticket.potentialGain;
                });
        }
    }

    sendBet() {
        const confirmationMessage = confirm(`Se va a proceder a enviar la apuesta, y descontar ${this.multiplicator}€ de su cuenta, ¿ Desea continuar ? `);
        if (confirmationMessage) {
            this.betService.sendBet(this.multiplicator)
                .then(ticket => {
                    this.potentialGainTemporary = 0.00;
                    this.getEgamesTicket();
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
