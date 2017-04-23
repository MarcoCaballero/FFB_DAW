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
    public alerts: any = [];
    sportsTeams: Team[];
    egamesTeams: Team[];
    newSportTeam: any = {};
    newEgameTeam: any = {};

    constructor(
        private teamService: TeamService
    ) { }

    ngOnInit() {
        this.getSportsTeams();
        this.getEgamesTeams();
    }

    public addAlert(msg: string): void {
        this.alerts.push({
            type: 'success',
            msg: msg,
            timeout: 5000
        });
    }

    newSportsTeam(team: Team) {
        console.log(team);
        this.teamService
            .newSportsTeam(team)
            .then(() => {
                this.getSportsTeams();
                this.addAlert('El equipo :  ' + team.name + ' ha sido correctamente añadido ');
            });

    }

    newEgamesTeam() {
        this.teamService
            .newEgamesTeam(this.newEgameTeam)
            .then(() => {
                this.getEgamesTeams();
                 this.addAlert('El equipo :  '  + ' ha sido correctamente añadido ');
            });
    }

    updateSportsTeam(team: Team) {
        this.teamService
            .updateSportsTeam(team)
            .then(() => {
                this.getSportsTeams();
            });
    }

    updateEgamesTeam(team: Team) {
        this.teamService
            .updateEgamesTeam(team)
            .then(() => {
                this.getEgamesTeams();
            });
    }

    removeTeam(team: Team) {
        const confirmationMessage = confirm('¿Estás seguro de que quieres borrar el equipo ' + team.name + '?');
        if (confirmationMessage) {
            this.teamService
                .deleteTeam(team.id)
                .then(() => {
                    this.getSportsTeams();
                    this.getEgamesTeams();
                });
        }
    }

    getSportsTeams() {
        this.teamService.getSportsTeams().then(sportsTeams => this.sportsTeams = sportsTeams);
    }
    getEgamesTeams() {
        this.teamService.getEgamesTeams().then(egamesTeams => this.egamesTeams = egamesTeams);
    }
}
