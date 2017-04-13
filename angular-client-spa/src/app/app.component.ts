import { Component, OnInit } from '@angular/core';

@Component({
    moduleId: module.id,
    selector: 'ffbcomp-root',
    template: `
    <ffbcomp-user></ffbcomp-user>
  `
})

export class AppComponent implements OnInit {
    constructor() { }

    ngOnInit() { }

}
