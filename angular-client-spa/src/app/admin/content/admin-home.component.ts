import { Component, OnInit } from '@angular/core';

import { UserService } from '../../services/user.service';

@Component({
    moduleId: module.id,
    selector: 'ffbcomp-admin-home',
    templateUrl: 'admin-home.component.html',
    styleUrls: ['../../../assets/css/admin/styles.css', '../../../assets/css/admin/media.css',
    '../../../assets/css/admin/bootstrap.min.css']
})

export class AdminHomeComponent implements OnInit {

    constructor(
        private userService: UserService
    ) {}

    ngOnInit() {
        console.log('admin: ' + this.userService.credentials);
    }
}
