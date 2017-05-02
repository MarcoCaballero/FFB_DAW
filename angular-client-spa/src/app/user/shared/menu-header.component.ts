import { Component, OnInit, OnDestroy, HostListener } from '@angular/core';
import { Router } from '@angular/router';
import { Subscription } from 'rxjs/Subscription';


import { User } from '../../model/user.model';

import { LoginService } from '../../services/login.service';
import { AuthService } from '../../services/auth.service';
import { UserService } from '../../services/user.service';

@Component({
    moduleId: module.id,
    selector: 'ffbcomp-user-menu-header',
    templateUrl: './menu-header.component.html',
    styleUrls: ['../../../assets/css/styles.css']
})
export class MenuHeaderComponent implements OnInit {
    // Public fields
    public isCollapsed = true; // !Important to keep menu visible
    user: User;

    subscription: Subscription;

    @HostListener('window:resize') setCollapsed() {
        this.isCollapsed = true;
    }

    constructor(private loginService: LoginService, private authService: AuthService, private userService: UserService,
        private router: Router) {
        this.subscription = userService.changeAnnounced$.subscribe(
            user => {
                userService
                    .getUser(user.id)
                    .then(userReboot => {
                        this.user = userReboot
                        console.log(`header received that  -${this.user.name}- is it ? ${this.user.isMen}`);
                    })
            });
    }

    ngOnInit() {
        this.authService.reloadAuth(); // Reload auth.service fields from localStorage

        this.user = this.authService.getUser();

        console.log(`header onInit  -${this.user.name}- is it ? ${this.user.isMen}`);

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


