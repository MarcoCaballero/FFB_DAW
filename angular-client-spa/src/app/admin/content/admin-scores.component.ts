import { Component, OnInit } from '@angular/core';

@Component({
    moduleId: module.id,
    selector: 'ffbcomp-admin-scores',
    templateUrl: 'admin-scores.component.html',
    styleUrls: ['../../../assets/css/admin/styles.css', '../../../assets/css/admin/media.css']
})

export class AdminScoresComponent implements OnInit {
    title = 'ADMIN - SCORES';

    constructor() { }

    ngOnInit() { }
}
