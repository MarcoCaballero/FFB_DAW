import { Component } from '@angular/core';
import { Router } from '@angular/router';

import { LoginService } from '../../services/login.service';

@Component({
    moduleId: module.id,
    selector: 'ffbcomp-user-menu-header',
    templateUrl: './menu-header.component.html',
    styleUrls: ['../../../assets/css/styles.css']
})
export class MenuHeaderComponent {

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


