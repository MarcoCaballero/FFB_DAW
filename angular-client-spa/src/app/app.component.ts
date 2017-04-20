import { Component, OnInit } from '@angular/core';

import { UserService } from './services/user.service';

@Component({
    moduleId: module.id,
    selector: 'ffbcomp-root',
    template: `
    <router-outlet></router-outlet>
  `
})

export class AppComponent implements OnInit {
    constructor(
        private userService: UserService
    ) { }

    ngOnInit() {
        this.userService.setCredentials(localStorage.getItem('credentials'));
    }

}
