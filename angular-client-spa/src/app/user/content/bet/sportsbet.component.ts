import { Component, OnInit } from '@angular/core';
import { SportMatch } from "app/model/sport-match.model";
import { ScoreService } from "app/services/score.service";

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

    constructor(private scoreService: ScoreService) { }

    ngOnInit() {
        this.getFootballMatchesFinished();
        this.getBasketballMatchesFinished();
    }

     getFootballMatchesFinished(){
        this.scoreService.getFootballMatches().then(sportMatches => this.footballMatches = sportMatches.filter(matches => matches.finished === false))
    }

    getBasketballMatchesFinished(){
        this.scoreService.getBasketballMatches().then(sportMatches => this.basketballMatches = sportMatches.filter(matches => matches.finished === false))
    }

}
