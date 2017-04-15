import { Component, OnInit } from '@angular/core';

@Component({
    moduleId: module.id,
    selector: 'ffbcomp-admin-home',
    templateUrl: 'admin-home.component.html',
    styleUrls: ['../../../assets/css/admin/styles.css', '../../../assets/css/admin/media.css']
})

export class AdminHomeComponent implements OnInit {
    title = 'ADMIN - HOME';

    constructor() { }

    ngOnInit() { }
}
