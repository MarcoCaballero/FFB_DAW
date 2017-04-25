import { Component, OnInit } from '@angular/core';
import { EgamesMatch } from 'app/model/egames-match.model';
import { MatchService } from 'app/services/match.service';

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

    constructor(private matchService: MatchService) { }

    ngOnInit() { 
        this.getLolEgamesMatches();
        this.getCsEgamesMatches();
    }

    getLolEgamesMatches() {
        this.matchService.getLolMatches()
        .then(egamesMatches => this.lolMatches = egamesMatches.filter(matches => matches.finished === false));
    }

    getCsEgamesMatches() {
        this.matchService.getCsMatches()
        .then(egamesMatches => this.csMatches = egamesMatches.filter(matches => matches.finished === false));
    }

}
