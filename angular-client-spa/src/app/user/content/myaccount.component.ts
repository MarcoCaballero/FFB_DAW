import { Component, OnInit, ViewChild } from '@angular/core';
import { TabsetComponent } from 'ngx-bootstrap';

@Component({
    moduleId: module.id,
    selector: 'ffbcomp-myaccount',
    templateUrl: 'myaccount.component.html',
    styleUrls: ['../../../assets/css/styles.css', '../../../assets/css/cardstyle.css', '../../../assets/css/cardstyletop.css']
})

export class MyAccountComponent implements OnInit {
    // Public fields
    public isCollapsed = false;

    public selectedTab = 0;

    @ViewChild('staticTabs') staticTabs: TabsetComponent;

    title = 'Mi cuenta';


    // Methods
    constructor() { }

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


}

