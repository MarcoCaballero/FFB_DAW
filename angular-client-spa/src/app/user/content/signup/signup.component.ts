import { Component, OnInit, ViewChild } from '@angular/core';
import { Router } from '@angular/router';

import { UserService } from '../../../services/user.service';

import { User } from '../../../model/user.model';

@Component({
    moduleId: module.id,
    selector: 'ffbcomp-signup',
    templateUrl: 'signup.component.html',
    styleUrls: ['../../../../assets/css/styles.css']
})

export class SignupComponent implements OnInit {

    public alerts: any = [];
    title = 'Registro';
    secondPassword: string;

    constructor(
        private userService: UserService,
        private router: Router
    ) { }

    ngOnInit() { }

    newUser(user: User) {
        if (this.secondPassword === user.password) {
            this.userService.newUser(user);
            this.router.navigate(['/login']);
        }

    }
}
