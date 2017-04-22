import { Component, OnInit } from '@angular/core';

import { ScoreService } from '../../services/score.service';

import { SportMatch } from '../../model/sport-match.model';
import { EgamesMatch } from '../../model/egames-match.model';


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

    constructor(
        private scoreService: ScoreService
    ) { }


    ngOnInit() {
        this.matchFootballResults = [];
        this.matchBasketballResults = [];
        this.scoreService.getFootballMatches().then(sportMatches => this.matchFootballResults = sportMatches);
        this.scoreService.getBasketballMatches().then(sportMatches => this.matchBasketballResults = sportMatches);
        this.scoreService.getLolMatches().then(egamesMatches => this.matchLolResults = egamesMatches);
    }

    updateSportMatch(matchFootBallResult: SportMatch){
        this.scoreService.updateMatchResult(matchFootBallResult);
        location.reload();
    }

    updateEgamesMatch(matchFootBallResult: SportMatch){
        
    }

    removeEgamesMatch(matchFootBallResult: SportMatch){
        
    }

    removeSportMatch(matchFootBallResult: SportMatch){
        const confirmationMessage = confirm('¿Estás seguro de que quieres borrar el partido ' + matchFootBallResult.homeTeam + '-' + matchFootBallResult.visitingTeam + '?');
        if(confirmationMessage){
                this.scoreService.removeMatchResult(matchFootBallResult.id);
                location.reload();
        }
    }
}
