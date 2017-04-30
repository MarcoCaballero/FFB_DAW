import { Component, OnInit, ViewChild } from '@angular/core';
import { TabsetComponent } from 'ngx-bootstrap';


import { User } from '../../../model/user.model';

import { UserService } from '../../../services/user.service';



@Component({
    moduleId: module.id,
    selector: 'ffbcomp-myaccount',
    templateUrl: 'myaccount.component.html',
    styleUrls: ['../../../../assets/css/styles.css', '../../../../assets/css/cardstyle.css', '../../../../assets/css/cardstyletop.css']
})

export class MyAccountComponent implements OnInit {
    // Public fields
    public isCollapsed = false;
    public selectedTab = 0;
    public userLogged: User;
    public readOnly = "readonly";

    @ViewChild('staticTabs') staticTabs: TabsetComponent;

    title = 'Mi cuenta';


    // Methods
    constructor(private userService: UserService) { }

    ngOnInit() {
        this.setUserLogged(JSON.parse(localStorage.getItem('user')));
        this.getUser(this.userLogged.id);
        console.log(this.userLogged);
    }

    public getUser(id: number) {
        this.userService
            .getUser(id)
            .then(response => this.userLogged = response)
    }

    public setUserLogged(user: User) {
        this.userLogged = user;
    }

    public collapsed(event: any): void {
        console.log(event);
    }

    public expanded(event: any): void {
        console.log(event);
    }

    selectTab(tab_id: number) {
        this.staticTabs.tabs[tab_id].active = true;
        this.selectedTab = tab_id;
    }

    public selectTabFromInner(tab_id: number) {
        this.selectedTab = tab_id;
    }

    uploadFile(event) {
        let file: File = event.target.files[0];
        const formData = new FormData();
        formData.append('file', file, file.name);
        console.log(file.name);
        let title = this.userService
            .uploadFile(formData)
            .then(response => {
                this.getUser(this.userLogged.id);
                console.log(this.userLogged)
            });
    }


}

