import { Component } from '@angular/core';
import { Router } from '@angular/router';

import { LoginService } from '../../services/login.service';

@Component({
    moduleId: module.id,
    selector: 'ffbcomp-admin-header',
    templateUrl: './admin-header.component.html',
    styleUrls: ['../../../assets/css/admin/media.css', '../../../assets/css/admin/styles.css',
        '../../../assets/css/admin/bootstrap.min.css']
})

export class AdminHeaderComponent {

    constructor(
        private loginService: LoginService,
        private router: Router
    ) { }

    logOut() {

        this.loginService.logOut().subscribe(
            response => {
                this.router.navigate(['/login']);
            },
            error => console.log('Error when trying to log out: ' + error)
        );
    }
}
