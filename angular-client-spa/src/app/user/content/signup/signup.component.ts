import { Component, OnInit } from '@angular/core';
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
        console.log(this.user.name);
        console.log(this.user.surname);
        console.log(this.user.secondSurname);
        console.log(this.user.dni);
        console.log(this.user.email);
        console.log(this.user.country);
        console.log(this.user.city);
        console.log(this.user.location);
        console.log(this.user.telephone);
        console.log(this.user.men);
        console.log(this.user.password);
        this.userService.newUser(this.user);
        // this.router.navigate(['/login']);
    }
}
