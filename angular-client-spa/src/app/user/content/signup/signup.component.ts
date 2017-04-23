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

    title = 'Registro';
    checkFields = false;
    user: any = {};
    @ViewChild('formName') myForm: HTMLFormElement;

    constructor(
        private userService: UserService,
        private router: Router
        ) { }


    isEmpty(str: HTMLInputElement) {
        if (str.value === '') {
            return true;
        }
        return false;
    }

    sendSingUp() {
        this.checkFields = true;
    }

    ngOnInit() { }

    newUser() {
        this.userService.newUser(this.user);
        this.router.navigate(['/login']);
    }
}
