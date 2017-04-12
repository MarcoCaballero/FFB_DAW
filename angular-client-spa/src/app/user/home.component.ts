import { Component, OnInit } from '@angular/core';

@Component({
    moduleId: module.id,
    selector: 'ffbcomp-home',
    // templateUrl: 'home.component.html'
    template: `
    <h1>{{title}}</h1>
    `
})

export class HomeComponent implements OnInit {
    // Public fields
    title = 'HOME';

    constructor() { }

    ngOnInit() { }
}
