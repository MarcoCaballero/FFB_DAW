import { Component, OnInit } from '@angular/core';

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
    user: User;

    constructor(
        private userService: UserService
    ) { }

    ngOnInit() {
        this.userService.getUsers().then(users => this.users = users);
    }

    upgrade(user: User) {
        user.roles = ['ROLE_USER', 'ROLE_ADMIN'];
        this.userService.updateRoleUser();
    }

    downgrade(user: User) {
        user.roles = ['ROLE_USER'];
        this.user = user;
        this.userService.updateRoleUser();
    }

    deleteUser(user: User) {
        const confirmationMessage = confirm('¿Estás seguro de que quieres borrar al usuario ' + user.id + '?');
        if (confirmationMessage) {
            this.userService.removeUser(user.id);
            location.reload();
        }
    }


}
