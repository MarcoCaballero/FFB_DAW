import { Component, OnInit } from '@angular/core';

import { ScoreService } from '../../services/score.service';

import { ById } from '../../core/sort-functions';

import { SportMatch } from '../../model/sport-match.model';
import { EgamesMatch } from '../../model/egames-match.model';
import { Team } from "app/model/team.model";


@Component({
    moduleId: module.id,
    selector: 'ffbcomp-admin-scores',
    templateUrl: 'admin-scores.component.html',
    styleUrls: ['../../../assets/css/admin/styles.css', '../../../assets/css/admin/media.css',
        '../../../assets/css/admin/bootstrap.min.css']
})

export class AdminScoresComponent implements OnInit {
    title = 'ADMIN - SCORES';

    private matchFootballResults: SportMatch[];
    private matchBasketballResults: SportMatch[];
    private matchLolResults: EgamesMatch[];
    private matchCsResults: EgamesMatch[];

    constructor(
        private scoreService: ScoreService
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


    updateSportMatch(matchSportResult: SportMatch){
        this.scoreService
        .updateSportMatchResult(matchSportResult)
        .then(()=> {
            this.getFootballSportsMatches();
            this.getBasketballSportsMatches();
        });
    }


    updateEgamesMatch(matchEgamesResult: EgamesMatch){
        console.log(JSON.stringify(matchEgamesResult));
        this.scoreService
        .updateEgamesMatchResult(matchEgamesResult)
        .then(()=>{
            this.getLolEgamesMatches();
            this.getCsEgamesMatches();
        })
    }


    removeEgamesMatch(matchEgamesResult: EgamesMatch){
        const confirmationMessage = confirm('¿Estás seguro de que quieres borrar el partido ' + matchEgamesResult.homeTeam + '-' + matchEgamesResult.visitingTeam + '?');
        if(confirmationMessage){
                this.scoreService
                .removeEgamesMatchResult(matchEgamesResult.id)
                .then(()=>{
                    this.getCsEgamesMatches();
                    this.getLolEgamesMatches();
                })
        }
    }


    removeSportMatch(matchSportResult: SportMatch){
        const confirmationMessage = confirm('¿Estás seguro de que quieres borrar el partido ' + matchSportResult.homeTeam + '-' + matchSportResult.visitingTeam + '?');
        if(confirmationMessage){
                this.scoreService
                .removeSportMatchResult(matchSportResult.id)
                .then(()=>{
                    this.getFootballSportsMatches();
                    this.getBasketballSportsMatches();
                });
        }
    }

    isWinnerTeam(match: EgamesMatch, team: string){
        if(match.winnerTeam === team){
            return true;
        }
        return false;
    }



    isFirstBloodTeam(match: EgamesMatch, team: string){
        if(match.firstBloodTeam === team){
            return true;
        }
        return false;
    }

    getFootballSportsMatches() {
        this.scoreService.getFootballMatches().then(sportMatches => this.matchFootballResults = sportMatches);
    }

    getBasketballSportsMatches() {
        this.scoreService.getBasketballMatches().then(sportMatches => this.matchBasketballResults = sportMatches);
    }

    getLolEgamesMatches(){
        this.scoreService.getLolMatches().then(egamesMatches => this.matchLolResults = egamesMatches);
    }


    getCsEgamesMatches(){
        this.scoreService.getCsMatches().then(egamesMatches => this.matchCsResults = egamesMatches);
    }

}
