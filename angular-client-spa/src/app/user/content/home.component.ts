import { Component, OnInit } from '@angular/core';
import { TabsetComponent } from 'ngx-bootstrap';

@Component({
    moduleId: module.id,
    selector: 'ffbcomp-home',
    templateUrl: 'home.component.html',
    styleUrls: ['../../../assets/css/styles.css']
})

export class HomeComponent implements OnInit {
    // Public fields
    title = 'HOME';


    constructor() { }

    ngOnInit() { }



}

