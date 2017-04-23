import { Component, OnInit, OnDestroy } from '@angular/core';

import { AuthService } from './services/auth.service';

@Component({
    moduleId: module.id,
    selector: 'ffbcomp-root',
    template: `
    <router-outlet></router-outlet>
  `
})

export class AppComponent implements OnInit, OnDestroy {
    constructor(
        private authService: AuthService
    ) { }

    ngOnInit() {
        this.authService.reloadAuth(); // Reload auth.service fields from localStorage
    }

    ngOnDestroy() {
        localStorage.clear();
    }

}
