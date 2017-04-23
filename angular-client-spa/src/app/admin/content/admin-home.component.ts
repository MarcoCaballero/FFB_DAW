import { Component, OnInit } from '@angular/core';

import { ById } from '../../core/sort-functions';

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
        this.getUsers();
    }

    upgrade(user: User) {

        user.roles = ['ROLE_USER', 'ROLE_ADMIN'];
        this.userService
            .updateUser(user)
            .then(() => {
                this.getUsers();
            });
    }

    downgrade(user: User) {
        user.roles = ['ROLE_USER'];
        this.userService.updateUser(user)
            .then(() => {

                this.getUsers();
            });
    }

    deleteUser(user: User) {
        const confirmationMessage = confirm('¿Estás seguro de que quieres borrar al usuario ' + user.id + '?');
        if (confirmationMessage) {
            this.userService
                .removeUser(user.id)
                .then(() => {

                    this.getUsers();
                }
                );
        }
    }

    getUsers() {
        this.userService.getUsers().then(users => this.users = users);
    }


}
