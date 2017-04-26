import { Component, OnInit } from '@angular/core';

import { MatchService } from '../../services/match.service';

import { Team } from '../../model/team.model';
import { SportMatch} from '../../model/sport-match.model';
import { EgamesMatch} from '../../model/egames-match.model';

@Component({
    moduleId: module.id,
    selector: 'ffbcomp-admin-matches',
    templateUrl: 'admin-matches.component.html',
    styleUrls: ['../../../assets/css/admin/styles.css', '../../../assets/css/admin/media.css',
    '../../../assets/css/admin/bootstrap.min.css']
})

export class AdminMatchesComponent implements OnInit {
    teams: Team[];
    sportsMatches: SportMatch[];
    egamesMatches: EgamesMatch[];

    constructor() { }

    ngOnInit() { }
}
