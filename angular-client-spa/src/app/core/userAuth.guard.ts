import { Injectable } from '@angular/core';
import { Router, CanActivate } from '@angular/router';
import { AuthService } from '../services/auth.service';

@Injectable()
export class UserAuthGuard implements CanActivate {

    constructor(private authService: AuthService, private router: Router) { }

    canActivate() {
        console.log('CanActivate User' + this.authService.isLogged());
        if (this.authService.isLogged()) {
            return true;
        }
        this.router.navigate(['/login']);
        return false;
    }
}
