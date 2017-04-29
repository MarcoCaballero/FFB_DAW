import { Component, OnInit, ViewChild } from '@angular/core';
import { TabsetComponent } from 'ngx-bootstrap';

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
    public filename: string;

    public selectedTab = 0;

    @ViewChild('staticTabs') staticTabs: TabsetComponent;

    title = 'Mi cuenta';


    // Methods
    constructor(private userService: UserService) { }

    ngOnInit() {

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
        this.filename = file.name;
        formData.append('file', file, file.name);
        console.log(file.name);
        let title = this.userService
            .uploadFile(formData)
            .then(response => { console.log(response) });
    }


}

