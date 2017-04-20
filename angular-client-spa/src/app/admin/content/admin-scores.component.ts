import { Component, OnInit } from '@angular/core';
import { Http, RequestOptions } from '@angular/http';


import { ScoreService } from '../../services/score.service';
import { SportMatch } from '../../model/sport-match.model';


@Component({
    moduleId: module.id,
    selector: 'ffbcomp-admin-scores',
    templateUrl: 'admin-scores.component.html',
    styleUrls: ['../../../assets/css/admin/styles.css', '../../../assets/css/admin/media.css',
        '../../../assets/css/admin/bootstrap.min.css']
})

export class AdminScoresComponent implements OnInit {
    title = 'ADMIN - SCORES';

    private matchFootballResults: SportMatch[] = [];

    constructor(private scoreService: ScoreService) { }


    ngOnInit() {
        this.matchFootballResults = this.scoreService.getFootballMatches();
        console.log(this.matchFootballResults);
    }
}
