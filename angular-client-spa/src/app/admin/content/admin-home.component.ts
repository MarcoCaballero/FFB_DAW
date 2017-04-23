import { Component, OnInit } from '@angular/core';

import { AuthService } from '../../services/auth.service';
import { UserService } from '../../services/user.service';

import { User } from '../../model/user.model';

@Component({
    moduleId: module.id,
    selector: 'ffbcomp-admin-home',
    templateUrl: 'admin-home.component.html',
    styleUrls: ['../../../assets/css/admin/styles.css', '../../../assets/css/admin/media.css',
        '../../../assets/css/admin/bootstrap.min.css']
})

export class AdminHomeComponent implements OnInit {
    users: User[];

    constructor(
        private userService: UserService, private authService: AuthService
    ) { }

    ngOnInit() {
        // this.authService.reloadAuth();
        this.userService.getUsers().then(users => this.users = users);
    }

    upgrade(user: User) {
        user.roles = ['ROLE_USER', 'ROLE_ADMIN'];
        this.userService.updateUser(user);
    }

    downgrade(user: User) {
        user.roles = ['ROLE_USER'];
        this.userService.updateUser(user);
    }

    deleteUser(user: User) {
        const confirmationMessage = confirm('¿Estás seguro de que quieres borrar al usuario ' + user.name + '?');
        if (confirmationMessage) {
            this.userService.removeUser(user.id);
            location.reload();
        }
    }


}
