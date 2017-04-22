import { Component, OnInit } from '@angular/core';

import { ById } from '../../core/sort-functions';

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
        private teamService: TeamService
    ) { }

    ngOnInit() {
        this.teamService.getSportsTeams().then(sportsTeams => this.sportsTeams = sportsTeams);
        this.teamService.getEgamesTeams().then(egamesTeams => this.egamesTeams = egamesTeams);
    }

    newSportsTeam() {
        this.teamService
            .newSportsTeam(this.newSportTeam)
            .then(() => {
                this.sportsTeams.push(this.newSportTeam);
            })

    }
    newEgamesTeam() {
        this.teamService
            .newEgamesTeam(this.newEgameTeam)
            .then(() => {
                this.egamesTeams.push(this.newEgameTeam);
            })
    }

    updateSportsTeam(team: Team) {
        this.teamService
            .updateSportsTeam(team)
            .then(() => {
                this.sportsTeams = this.sportsTeams.filter(t => t.id !== team.id);
                this.sportsTeams.push(team);
                this.sportsTeams.sort(ById);
            });
    }

    updateEgamesTeam(team: Team) {
        this.teamService
            .updateEgamesTeam(team)
            .then(() => {
                this.egamesTeams = this.egamesTeams.filter(t => t.id !== team.id);
                this.egamesTeams.push(team);
                this.egamesTeams.sort(ById);
            });
    }

    removeTeam(team: Team) {
        const confirmationMessage = confirm('¿Estás seguro de que quieres borrar el equipo ' + team.name + '?');
        if (confirmationMessage) {
            this.teamService
                .removeTeam(team.id)
                .then(() => {
                    if (team.type === "Fútbol" || team.type === "Baloncesto") {
                        this.sportsTeams = this.sportsTeams.filter(t => t !== team);
                    } else {
                        this.egamesTeams = this.egamesTeams.filter(t => t !== team);
                    }
                })

        }
    }
}
