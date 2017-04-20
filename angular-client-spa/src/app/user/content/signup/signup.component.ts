import { Component, OnInit } from '@angular/core';

@Component({
    moduleId: module.id,
    selector: 'ffbcomp-signup',
    templateUrl: 'signup.component.html',
    styleUrls: ['../../../../assets/css/styles.css']
})

export class SignupComponent implements OnInit {

    title = 'Registro';
    checkFields = false;

    constructor() { }


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
}
