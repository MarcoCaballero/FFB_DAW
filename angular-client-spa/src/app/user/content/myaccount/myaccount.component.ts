import { Component, OnInit, ViewChild } from '@angular/core';
import { TabsetComponent } from 'ngx-bootstrap';


import { User } from '../../../model/user.model';
import { BetTicket } from '../../../model/bet-ticket.model';
import { Team } from '../../../model/team.model';
import { SportMatch } from '../../../model/sport-match.model';

import { AuthService } from '../../../services/auth.service';
import { UserService } from '../../../services/user.service';
import { TeamService } from '../../../services/team.service';
import { MatchService } from '../../../services/match.service';
import { BetService } from '../../../services/bet.service';

@Component({
    moduleId: module.id,
    selector: 'ffbcomp-myaccount',
    templateUrl: 'myaccount.component.html',
    styleUrls: ['../../../../assets/css/styles.css', '../../../../assets/css/cardstyle.css', '../../../../assets/css/cardstyletop.css']
})

export class MyAccountComponent implements OnInit {
    // Public fields
    public isCollapsed = false;
    public selectedTab = 0;
    public userLogged: User;
    private fileAux: FormData;
    tickets: BetTicket[];
    finishedTickets: BetTicket[];
    amount = 0;
    potentialGain = 0;
    teams: Team[];
    selectedTeam: Team;
    matches: SportMatch[];
    finishedMatches: SportMatch[];

    @ViewChild('staticTabs') staticTabs: TabsetComponent;

    title = 'Mi cuenta';


    // Methods
    constructor(
        private authService: AuthService,
        private userService: UserService,
        private teamService: TeamService,
        private matchService: MatchService,
        private betService: BetService
    ) { }

    ngOnInit() {
        this.setUserLogged(this.authService.getUser());
        console.log(`upload onInit  -${this.userLogged.name}- is it ? ${this.userLogged.isMen}`);
        this.getTeams();
        this.getTickets(this.userLogged);
        this.getFinishedTickets(this.userLogged);
    }

    public getUser(id: number) {
        this.userService
            .getUser(id)
            .then(response => {
                this.userLogged = response;
                this.authService.buildUser(response);
            });

    }

    public setUserLogged(user: User) {
        this.userLogged = user;
    }

    public collapsed(event: any): void {
        console.log(event);
    }

    public expanded(event: any): void {
        console.log(event);
    }

    public selectTab(tab_id: number) {
        this.staticTabs.tabs[tab_id].active = true;
        this.selectedTab = tab_id;
    }

    public selectTabFromInner(tab_id: number) {
        this.selectedTab = tab_id;
    }

    public uploadFile(event) {
        const file: File = event.target.files[0];
        const formData = new FormData();
        formData.append('file', file, file.name);
        this.fileAux = formData;

    }
    public uploadUser() {
        this.userService
            .uploadFile(this.fileAux)
            .then(response => {
                this.getUser(this.userLogged.id);
                this.userService.announceChange(this.userLogged);
                console.log(`upload changed  -${this.userLogged.name}- is it ? ${this.userLogged.isMen} announced`);
            });
    }

    public getTickets(user: User) {
        this.userService.getTickets(user)
            .then(
            tickets => {
                this.tickets = tickets.filter(ticket => ticket.finished === false);
                this.tickets.forEach(element => {
                    this.amount += element.amount;
                    this.potentialGain += element.potentialGain;
                });
            }
            );
    }

    public getFinishedTickets(user: User) {
        this.userService.getTickets(user)
            .then(tickets => {
                this.finishedTickets = tickets
                    .filter(finishedTickets => finishedTickets.finished === true);
            });
    }

    public getTeams() {
        this.teamService.getSportsTeams().then(teams => this.teams = teams
            .filter(footballTeams => footballTeams.type === 'Fútbol'));
    }

    public selectTeam(team: Team) {
        this.selectedTeam = team;
        this.getMatches(this.selectedTeam);
        this.getFinishedMatches();
        this.isCollapsed = false;
    }

    public getMatches(team: Team) {
        this.matchService.getFootballMatches()
            .then(match => this.matches = match
                .filter(footballMatches => footballMatches.id === team.id));
    }

    public getFinishedMatches() {
        this.finishedMatches = this.matches.filter(finishedMatches => finishedMatches.finished === true);
    }

    public validateTicket(ticket: BetTicket) {
        this.betService.validateTicket(ticket)
            .then(() => {
                this.getFinishedTickets(this.userLogged);
                this.getUser(this.userLogged.id);
            });
    }

    public deleteTicket(ticket: BetTicket) {
        this.betService.deleteTicket(ticket)
            .then(() => {
                this.getFinishedTickets(this.userLogged);
                this.getUser(this.userLogged.id);
            });
    }

}

