import { Injectable } from '@angular/core';
import { Router, CanActivate } from '@angular/router';
import { AuthService } from '../services/auth.service';

@Injectable()
export class AdminAuthGuard implements CanActivate {

    constructor(private authService: AuthService, private router: Router) { }

    canActivate() {
        console.log('CanActivate Admin' + this.authService.isAdmin());
        if (this.authService.isAdmin())
            return true;
        this.router.navigate(['/login']);
        return false;
    }
}
