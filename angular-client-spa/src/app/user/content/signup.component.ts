import { Component, OnInit } from '@angular/core';

@Component({
    moduleId: module.id,
    selector: 'ffbcomp-signup',
    templateUrl: 'signup.component.html',
    styleUrls: ['../../../assets/css/styles.css']
})

export class SignupComponent implements OnInit {

    title = 'Registro';

    constructor() { }

    ngOnInit() { }
}
