import { Component, OnInit } from '@angular/core';

import { TeamService } from '../../services/team.service';

import { Team } from '../../model/team.model';

@Component({
    moduleId: module.id,
    selector: 'ffbcomp-admin-teams',
    templateUrl: 'admin-teams.component.html',
    styleUrls: ['../../../assets/css/admin/styles.css', '../../../assets/css/admin/media.css',
        '../../../assets/css/admin/bootstrap.min.css']
})

export class AdminTeamsComponent implements OnInit {
    sportTeams: Team[];

    constructor(
        private teamService: TeamService
    ) { }

    ngOnInit() {
        this.sportTeams = [];
        this.teamService.getSportsTeams().then(sportTeams => this.sportTeams = sportTeams);
        this.checkSportTeam();
    }

    checkSportTeam() {
        console.log(this.sportTeams);
    }
}
