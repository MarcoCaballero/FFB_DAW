import { Component, OnInit } from '@angular/core';
import { TabsetComponent } from 'ngx-bootstrap';
import { SportMatch } from 'app/model/sport-match.model';
import { MatchService } from 'app/services/match.service';
import { EgamesMatch } from 'app/model/egames-match.model';

@Component({
    moduleId: module.id,
    selector: 'ffbcomp-home',
    templateUrl: 'home.component.html',
    styleUrls: ['../../../assets/css/styles.css']
})

export class HomeComponent implements OnInit {
    public footballMatches: SportMatch[];
    public basketballMatches: SportMatch[];
    public lolMatches: EgamesMatch[];
    public csMatches: EgamesMatch[];

    constructor(
        private matchService: MatchService
        ) {}

    ngOnInit() {
        this.getFootballMatchesFinished();
        this.getBasketballMatchesFinished();
        this.getLolMatchesFinished();
        this.getCsMatchesFinished();
    }

    getFootballMatchesFinished(){
        this.matchService.getFootballMatches()
        .then(sportMatches => this.footballMatches = sportMatches.filter(matches => matches.finished === true));
    }

    getBasketballMatchesFinished(){
        this.matchService.getBasketballMatches()
        .then(sportMatches => this.basketballMatches = sportMatches.filter(matches => matches.finished === true));
    }

    getLolMatchesFinished() {
        this.matchService.getLolMatches()
        .then(egamesMatches => this.lolMatches = egamesMatches.filter(matches => matches.finished === true));
    }

    getCsMatchesFinished() {
        this.matchService.getCsMatches()
        .then(egamesMatches => this.csMatches = egamesMatches.filter(matches => matches.finished === true));
    }


}
