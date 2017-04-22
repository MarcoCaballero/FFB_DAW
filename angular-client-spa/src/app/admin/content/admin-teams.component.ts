import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

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
    sportsTeams: Team[];
    egamesTeams: Team[];
    newSportTeam: any = {};
    newEgameTeam: any = {};

    constructor(
        private teamService: TeamService,
        private router: Router
    ) { }

    ngOnInit() {
        this.teamService.getSportsTeams().then(sportsTeams => this.sportsTeams = sportsTeams);
        this.teamService.getEgamesTeams().then(egamesTeams => this.egamesTeams = egamesTeams);
    }

    newSportsTeam() {
        this.teamService.newSportsTeam(this.newSportTeam);
        location.reload();
    }
    newEgamesTeam() {
        this.teamService.newEgamesTeam(this.newEgameTeam);
        location.reload();
    }

    updateSportsTeam(team: Team) {
        this.teamService.updateSportsTeam(team);
        location.reload();
    }

    updateEgamesTeam(team: Team) {
        this.teamService.updateEgamesTeam(team);
        location.reload();
    }

    removeTeam(team: Team) {
        const confirmationMessage = confirm('¿Estás seguro de que quieres borrar el equipo ' + team.name + '?');
        if (confirmationMessage) {
            this.teamService.removeTeam(team.id);
            location.reload();
        }
    }
}
