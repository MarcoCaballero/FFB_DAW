import { Component, OnInit, OnDestroy, HostListener } from '@angular/core';

import { AuthService } from './services/auth.service';

@Component({
    moduleId: module.id,
    selector: 'ffbcomp-root',
    host: { 'window:beforeunload': 'clearAuth' },
    template: `
    <router-outlet></router-outlet>
  `
})

export class AppComponent implements OnInit, OnDestroy {
    @HostListener('window:beforeunload') clearAuth() {
        console.log('Cleaning credential authorization.....');
        this.authService.clear();
        localStorage.clear();
    }

    constructor(
        private authService: AuthService
    ) { }


    ngOnInit() {
        this.authService.reloadAuth(); // Reload auth.service fields from localStorage
    }

    ngOnDestroy() {
        console.log('Cleaning credential authorization.....');
        this.authService.clear();
        localStorage.clear();
    }

}
