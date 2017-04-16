import { Component, OnInit } from '@angular/core';

@Component({
    moduleId: module.id,
    selector: 'ffbcomp-admin-teams',
    templateUrl: 'admin-teams.component.html',
    styleUrls: ['../../../assets/css/admin/styles.css', '../../../assets/css/admin/media.css',
    '../../../assets/css/admin/bootstrap.min.css']
})

export class AdminTeamsComponent implements OnInit {
    title = 'ADMIN - TEAMS';

    constructor() { }

    ngOnInit() { }
}
