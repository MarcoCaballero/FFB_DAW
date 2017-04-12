import { Component, OnInit } from '@angular/core';

@Component({
    moduleId: module.id,
    selector: 'ffbcomp-root',
    template: `
    <ffbcomp-user-menu-header></ffbcomp-user-menu-header>
  `
})

export class AppComponent implements OnInit {
    constructor() { }

    ngOnInit() { }

}
