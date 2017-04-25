import { Component, OnInit } from '@angular/core';
import { SportMatch } from 'app/model/sport-match.model';
import { MatchService } from 'app/services/match.service';

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

    constructor(private matchService: MatchService) { }

    ngOnInit() {
        this.getFootballMatchesFinished();
        this.getBasketballMatchesFinished();
    }

     getFootballMatchesFinished() {
        this.matchService.getFootballMatches()
        .then(sportMatches => this.footballMatches = sportMatches.filter(matches => matches.finished === false));
    }

    getBasketballMatchesFinished() {
        this.matchService.getBasketballMatches()
        .then(sportMatches => this.basketballMatches = sportMatches.filter(matches => matches.finished === false));
    }

}
