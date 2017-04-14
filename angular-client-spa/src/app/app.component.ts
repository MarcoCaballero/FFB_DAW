import { Component, OnInit } from '@angular/core';

@Component({
    moduleId: module.id,
    selector: 'ffbcomp-root',
    template: `
    <ffbcomp-admin></ffbcomp-admin>
  `
})

export class AppComponent implements OnInit {
    constructor() { }

    ngOnInit() { }

}
