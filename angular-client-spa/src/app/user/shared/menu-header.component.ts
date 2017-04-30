import { Component, OnInit, OnDestroy, HostListener } from '@angular/core';
import { Router } from '@angular/router';

import { LoginService } from '../../services/login.service';
import { AuthService } from '../../services/auth.service';

@Component({
    moduleId: module.id,
    selector: 'ffbcomp-user-menu-header',
    templateUrl: './menu-header.component.html',
    styleUrls: ['../../../assets/css/styles.css']
})
export class MenuHeaderComponent implements OnInit {
    // Public fields
    public isCollapsed = true; // !Important to keep menu visible
    @HostListener('window:resize') setCollapsed() {
        this.isCollapsed = true;
    }

    constructor(
        private loginService: LoginService,
        private authService: AuthService,
        private router: Router
    ) { }

    ngOnInit() {
        this.authService.reloadAuth(); // Reload auth.service fields from localStorage
    }

    logOut() {
        this.loginService.logOut().subscribe(
            response => {
                this.router.navigate(['/login']);
            },
            error => console.log('Error when trying to log out: ' + error)
        );
    }

    public collapsed(event: any): void {
        console.log(event);
    }

    public expanded(event: any): void {
        console.log(event);
    }


}


