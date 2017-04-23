import { Component, OnInit, OnDestroy, HostListener } from '@angular/core';

import { AuthService } from './services/auth.service';

@Component({
    moduleId: module.id,
    selector: 'ffbcomp-root',
    template: `
    <router-outlet></router-outlet>
  `
})

export class AppComponent implements OnInit, OnDestroy {
    @HostListener('window:onclose') clearAuth() {
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
