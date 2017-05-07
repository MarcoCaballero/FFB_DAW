import { Component, OnInit } from '@angular/core';

import { MatchService } from '../../services/match.service';

import { ById } from '../../core/sort-functions';

import { SportMatch } from '../../model/sport-match.model';
import { EgamesMatch } from '../../model/egames-match.model';
import { Team } from '../../model/team.model';


@Component({
    moduleId: module.id,
    selector: 'ffbcomp-admin-scores',
    templateUrl: 'admin-scores.component.html',
    styleUrls: ['../../../assets/css/admin/styles.css', '../../../assets/css/admin/media.css',
        '../../../assets/css/admin/bootstrap.min.css']
})

export class AdminScoresComponent implements OnInit {
    public matchFootballResults: SportMatch[];
    public matchBasketballResults: SportMatch[];
    public matchLolResults: EgamesMatch[];
    public matchCsResults: EgamesMatch[];

    constructor(
        private matchService: MatchService
    ) { }


    ngOnInit() {
        this.matchFootballResults = [];
        this.matchBasketballResults = [];
        this.matchLolResults = [];
        this.matchCsResults = [];
        this.getFootballSportsMatches();
        this.getBasketballSportsMatches();
        this.getLolEgamesMatches();
        this.getCsEgamesMatches();

    }


    updateSportMatch(matchSportResult: SportMatch) {
        this.matchService
            .updateSportMatchResult(matchSportResult)
            .then(() => {
                this.getFootballSportsMatches();
                this.getBasketballSportsMatches();
            });
    }


    updateEgamesMatch(matchEgamesResult: EgamesMatch) {
        console.log(JSON.stringify(matchEgamesResult));
        this.matchService
            .updateEgamesMatchResult(matchEgamesResult)
            .then(() => {
                this.getLolEgamesMatches();
                this.getCsEgamesMatches();
            });
    }


    removeEgamesMatch(matchEgamesResult: EgamesMatch) {
        const confirmationMessage = confirm('¿Estás seguro de que quieres borrar el partido '
        + matchEgamesResult.homeTeam + '-' + matchEgamesResult.visitingTeam + '?');
        if (confirmationMessage) {
            this.matchService
                .removeEgamesMatchResult(matchEgamesResult.id)
                .then(() => {
                    this.getCsEgamesMatches();
                    this.getLolEgamesMatches();
                });
        }
    }


    removeSportMatch(matchSportResult: SportMatch) {
        const confirmationMessage = confirm('¿Estás seguro de que quieres borrar el partido '
        + matchSportResult.homeTeam + '-' + matchSportResult.visitingTeam + '?');
        if (confirmationMessage) {
            this.matchService
                .removeSportMatchResult(matchSportResult.id)
                .then(() => {
                    this.getFootballSportsMatches();
                    this.getBasketballSportsMatches();
                });
        }
    }

    isWinnerTeam(match: EgamesMatch, team: string): boolean {
        if (match.winnerTeam === team) {
            return true;
        }
        return false;
    }

    isFirstBloodTeam(match: EgamesMatch, team: string): boolean {
        if (match.firstBloodTeam === team) {
            return true;
        }
        return false;
    }

    getFootballSportsMatches() {
        this.matchService.getFootballMatches()
        .then(sportMatches => this.matchFootballResults = sportMatches
        .filter(matches => matches.finished === false));
    }

    getBasketballSportsMatches() {
        this.matchService.getBasketballMatches()
        .then(sportMatches => this.matchBasketballResults = sportMatches
        .filter(matches => matches.finished === false));
    }

    getLolEgamesMatches() {
        this.matchService.getLolMatches().then(egamesMatches => this.matchLolResults = egamesMatches
        .filter(matches => matches.finished === false));
    }


    getCsEgamesMatches() {
        this.matchService.getCsMatches().then(egamesMatches => this.matchCsResults = egamesMatches.
        filter(matches => matches.finished === false));
    }



}
