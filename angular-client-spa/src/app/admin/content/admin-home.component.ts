import { Component, OnInit } from '@angular/core';

import { UserService } from '../../services/user.service';

import { AuthService } from '../../services/auth.service';

@Component({
    moduleId: module.id,
    selector: 'ffbcomp-admin-home',
    templateUrl: 'admin-home.component.html',
    styleUrls: ['../../../assets/css/admin/styles.css', '../../../assets/css/admin/media.css',
        '../../../assets/css/admin/bootstrap.min.css']
})

export class AdminHomeComponent implements OnInit {

    constructor(
        private userService: UserService, private authService: AuthService
    ) { }

    ngOnInit() {
        this.authService.reloadAuth();
        console.log('User' + this.authService.getUser() + 'Credentials' + this.authService.getCredentials())
    }
}
