import { Component, OnInit } from '@angular/core';

@Component({
    selector: 'user-footer',
    templateUrl: './user-footer.component.html',
     styleUrls: ['../assets/css/styles.css']
})

export class UserFooterComponent implements OnInit {

    title = "soy footer!";
    constructor() { }

    ngOnInit() { }
}