import { Component, OnInit } from '@angular/core';
import { TabsetComponent } from 'ngx-bootstrap';
import { SportMatch } from "app/model/sport-match.model";
import { ScoreService } from "app/services/score.service";
import { EgamesMatch } from "app/model/egames-match.model";

@Component({
    moduleId: module.id,
    selector: 'ffbcomp-home',
    templateUrl: 'home.component.html',
    styleUrls: ['../../../assets/css/styles.css']
})

export class HomeComponent implements OnInit {
    // Public fields
    title = 'HOME';

    
    private footballMatches: SportMatch[];
    private basketballMatches: SportMatch[];
    private lolMatches: EgamesMatch[];
    private csMatches: EgamesMatch[];
    
    constructor(
        private scoreService: ScoreService
        ) {}

    ngOnInit() { 
        this.getFootballMatchesFinished();
        this.getBasketballMatchesFinished();
        this.getLolMatchesFinished();
        this.getCsMatchesFinished();
    }

    getFootballMatchesFinished(){
        this.scoreService.getFootballMatches().then(sportMatches => this.footballMatches = sportMatches.filter(matches => matches.finished === true))
    }

    getBasketballMatchesFinished(){
        this.scoreService.getBasketballMatches().then(sportMatches => this.basketballMatches = sportMatches.filter(matches => matches.finished === true))
    }

    getLolMatchesFinished(){
        this.scoreService.getLolMatches().then(egamesMatches => this.lolMatches = egamesMatches.filter(matches => matches.finished === true))
    }

    getCsMatchesFinished(){
        this.scoreService.getCsMatches().then(egamesMatches => this.csMatches = egamesMatches.filter(matches => matches.finished === true))
    }


}
