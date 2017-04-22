import { Component, OnInit } from '@angular/core';

import { ScoreService } from '../../services/score.service';

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
        this.scoreService.getFootballMatches().then(sportMatches => this.matchFootballResults = sportMatches);
        this.scoreService.getBasketballMatches().then(sportMatches => this.matchBasketballResults = sportMatches);
        this.scoreService.getLolMatches().then(egamesMatches => this.matchLolResults = egamesMatches);
        this.scoreService.getCsMatches().then(egamesMatches => this.matchCsResults = egamesMatches);
    }

    updateSportMatch(matchSportResult: SportMatch){
        this.scoreService.updateSportMatchResult(matchSportResult);
        location.reload();
    }

    updateEgamesMatch(matchEgamesResult: EgamesMatch){
        this.scoreService.updateEgamesMatchResult(matchEgamesResult);
        location.reload();
    }


    removeEgamesMatch(matchLolResult: EgamesMatch){
        const confirmationMessage = confirm('¿Estás seguro de que quieres borrar el partido ' + matchLolResult.homeTeam + '-' + matchLolResult.visitingTeam + '?');
        if(confirmationMessage){
                this.scoreService.removeEgamesMatchResult(matchLolResult.id);
                location.reload();
        }
    }

    removeSportMatch(matchFootBallResult: SportMatch){
        const confirmationMessage = confirm('¿Estás seguro de que quieres borrar el partido ' + matchFootBallResult.homeTeam + '-' + matchFootBallResult.visitingTeam + '?');
        if(confirmationMessage){
                this.scoreService.removeSportMatchResult(matchFootBallResult.id);
                location.reload();
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

    
}
