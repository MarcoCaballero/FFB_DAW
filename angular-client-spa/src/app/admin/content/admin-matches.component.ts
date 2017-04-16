import { Component, OnInit } from '@angular/core';

@Component({
    moduleId: module.id,
    selector: 'ffbcomp-admin-matches',
    templateUrl: 'admin-matches.component.html',
    styleUrls: ['../../../assets/css/admin/styles.css', '../../../assets/css/admin/media.css',
    '../../../assets/css/admin/bootstrap.min.css']
})

export class AdminMatchesComponent implements OnInit {
    title = 'ADMIN - MATCHES';

    constructor() { }

    ngOnInit() { }
}
