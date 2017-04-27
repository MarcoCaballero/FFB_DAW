import { Component, OnInit } from '@angular/core';

import { MatchService } from '../../services/match.service';
import { TeamService } from '../../services/team.service';

import { Team } from '../../model/team.model';
import { SportMatch } from '../../model/sport-match.model';
import { EgamesMatch } from '../../model/egames-match.model';

@Component({
    moduleId: module.id,
    selector: 'ffbcomp-admin-matches',
    templateUrl: 'admin-matches.component.html',
    styleUrls: ['../../../assets/css/admin/styles.css', '../../../assets/css/admin/media.css',
        '../../../assets/css/admin/bootstrap.min.css']
})

export class AdminMatchesComponent implements OnInit {
    teams: Team[];
    footballTeams: Team[];
    basketballTeams: Team[];
    lolTeams: Team[];
    csTeams: Team[];
    footballMatches: SportMatch[];
    basketballMatches: SportMatch[];
    lolMatches: EgamesMatch[];
    csMatches: EgamesMatch[];
    public alerts: any = [];
    public badAlerts: any = [];

    constructor(
        private matchService: MatchService,
        private teamService: TeamService
    ) { }

    ngOnInit() {
        this.getFootballMatches();
        this.getBasketballMatches();
        this.getLolMatches();
        this.getCsMatches();
        this.getTeams();
        this.getFootballTeams();
        this.getBasketballTeams();
        this.getLOLTeams();
        this.getCSTeams();
    }

    public addAlert(msg: string): void {
        this.alerts.push({
            type: 'success',
            msg: msg,
            timeout: 5000
        });
    }

    public addBadAlert(msg: string): void {
        this.alerts.push({
            type: 'danger',
            msg: msg,
            timeout: 5000
        });
    }

    checkTeams(home: string, visiting: string) {
        if (home === visiting) {
            return true;
        }
        return false;
    }

    checkQuotas(home: number, visiting: number, draw: number, fbHome: number, fbVisiting: number) {
        if (fbHome === 0 && fbVisiting === 0) {
            if (home + visiting + draw !== 110) {
                return true;
            }
        } else {
            if (home + visiting + draw !== 110 || fbHome + fbVisiting !== 110) {
                return true;
            }
        }
        return false;
    }

    newFootballMatch(match: SportMatch) {
        match.type = 'Fútbol';
        match.time = match.time.concat(':00');
        this.checkTeams(match.homeTeam, match.visitingTeam);
        if (!this.checkTeams(match.homeTeam, match.visitingTeam) &&
            !this.checkQuotas(match.quotaHomeVictory, match.quotaVisitingVictory, match.quotaDraw, 0, 0)) {
            this.matchService.newSportMatch(match)
                .then(() => {
                    this.getFootballMatches();
                    this.addAlert('Se ha añadido un nuevo partido de fútbol');
                });
        } else {
            this.addBadAlert('Comprueba que los equipos no sean iguales y que las quotas sumen 110');
        }
    }

    newBasketballMatch(match: SportMatch) {
        match.type = 'Baloncesto';
        match.time = match.time.concat(':00');
        if (!this.checkTeams(match.homeTeam, match.visitingTeam) &&
            !this.checkQuotas(match.quotaHomeVictory, match.quotaVisitingVictory, match.quotaDraw, 0, 0)) {
            this.matchService.newSportMatch(match)
                .then(() => {
                    this.getBasketballMatches();
                    this.addAlert('Se ha añadido un nuevo partido de baloncesto');
                });
        } else {
            this.addBadAlert('Comprueba que los equipos no sean iguales y que las quotas sumen 110');
        }
    }

    newLOLMatch(match: EgamesMatch) {
        match.type = 'LOL';
        match.time = match.time.concat(':00');
         if (!this.checkTeams(match.homeTeam, match.visitingTeam) &&
            !this.checkQuotas(match.quotaHomeVictory, match.quotaVisitingVictory, 0,
            match.quotaHomeFirstBlood, match.quotaVisitingFirstBlood)) {
                this.matchService.newEgamesMatch(match)
                    .then(() => {
                        this.getLolMatches();
                        this.addAlert('Se ha añadido un nuevo partido de LOL');
                    });
            } else {
                this.addBadAlert('Comprueba que los equipos no sean iguales y que cada para de quotas sumen 110');
            }
    }

    newCSMatch(match: EgamesMatch) {
        match.type = 'CS-GO';
        match.time = match.time.concat(':00');
         if (!this.checkTeams(match.homeTeam, match.visitingTeam) &&
            !this.checkQuotas(match.quotaHomeVictory, match.quotaVisitingVictory, 0,
            match.quotaHomeFirstBlood, match.quotaVisitingFirstBlood)) {
                this.matchService.newEgamesMatch(match)
                    .then(() => {
                        this.getCsMatches();
                        this.addAlert('Se ha añadido un nuevo partido de CS-GO');
                    });
            } else {
                this.addBadAlert('Comprueba que los equipos no sean iguales y que cada para de quotas sumen 110');
            }
    }

    removeSportMatch(match: SportMatch) {
        const confirmationMessage = confirm('¿Estás seguro de que quieres borrar el equipo con id '
            + match.id + '?');
        this.matchService.removeSportMatchResult(match.id)
            .then(() => {
                this.getFootballMatches();
                this.getBasketballMatches();
            });
    }

    removeEgamesMatch(match: EgamesMatch) {
        const confirmationMessage = confirm('¿Estás seguro de que quieres borrar el equipo con id '
            + match.id + '?');
        this.matchService.removeEgamesMatchResult(match.id)
            .then(() => {
                this.getLolMatches();
                this.getCsMatches();
            });
    }

    getFootballMatches() {
        this.matchService.getFootballMatches()
            .then(footballMatches => this.footballMatches = footballMatches);
    }

    getBasketballMatches() {
        this.matchService.getBasketballMatches()
            .then(basketballMatches => this.basketballMatches = basketballMatches
            );
    }

    getLolMatches() {
        this.matchService.getLolMatches()
            .then(lolMatches => this.lolMatches = lolMatches);
    }

    getCsMatches() {
        this.matchService.getCsMatches()
            .then(csMatches => this.csMatches = csMatches);
    }

    getTeams() {
        this.teamService.getTeams()
            .then(teams => this.teams = teams);
    }

    getFootballTeams() {
        this.teamService.getSportsTeams()
            .then(footballTeams => this.footballTeams = footballTeams
                .filter(teams => teams.type === 'Fútbol'));
    }

    getBasketballTeams() {
        this.teamService.getSportsTeams()
            .then(basketballTeams => this.basketballTeams = basketballTeams
                .filter(teams => teams.type === 'Baloncesto'));
    }

    getLOLTeams() {
        this.teamService.getEgamesTeams()
            .then(lolTeams => this.lolTeams = lolTeams
                .filter(teams => teams.type === 'LOL'));
    }

    getCSTeams() {
        this.teamService.getEgamesTeams()
            .then(csTeams => this.csTeams = csTeams
                .filter(teams => teams.type === 'CS-GO'));
    }
}
