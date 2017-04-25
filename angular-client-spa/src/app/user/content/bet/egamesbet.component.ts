import { Component, OnInit } from '@angular/core';
import { EgamesMatch } from "app/model/egames-match.model";
import { ScoreService } from "app/services/score.service";

@Component({
    moduleId: module.id,
    selector: 'ffbcomp-egamesbet',
    templateUrl: 'egamesbet.component.html',
    styleUrls: ['../../../../assets/css/styles.css']
})

export class EgamesbetComponent implements OnInit {

    title = 'Apuestas electrónicas';
    private lolMatches: EgamesMatch[];
    private csMatches: EgamesMatch[];

    constructor(private scoreService: ScoreService) { }

    ngOnInit() { 
        this.getLolEgamesMatches();
        this.getCsEgamesMatches();
    }

    getLolEgamesMatches() {
        this.scoreService.getLolMatches().then(egamesMatches => this.lolMatches = egamesMatches.filter(matches => matches.finished === false));
    }

    getCsEgamesMatches() {
        this.scoreService.getCsMatches().then(egamesMatches => this.csMatches = egamesMatches.filter(matches => matches.finished === false));
    }

}
