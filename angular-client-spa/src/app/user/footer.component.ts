import { Component, OnInit } from '@angular/core';

@Component({
    moduleId: module.id,
    selector: 'ffbcomp-user-footer',
    templateUrl: './footer.component.html',
    styleUrls: ['../../assets/css/styles.css']
})

export class FooterComponent implements OnInit {

    title = 'soy footer!';
    constructor() { }

    ngOnInit() { }
}
