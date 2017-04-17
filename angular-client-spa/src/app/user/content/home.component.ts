import { Component, OnInit, ViewChild } from '@angular/core';
import { TabsetComponent } from 'ngx-bootstrap';

@Component({
    moduleId: module.id,
    selector: 'ffbcomp-home',
    templateUrl: 'home.component.html',
    styleUrls: ['../../../assets/css/styles.css']
})

export class HomeComponent implements OnInit {
    // Public fields
    @ViewChild('staticTabs') staticTabs: TabsetComponent;

    title = 'HOME';


    constructor() { }

    ngOnInit() { }

    // Could be used to switch enabled/disabled a tab from out of tabs
    disableEnable(tab_id: number) {
        this.staticTabs.tabs[tab_id].disabled = !this.staticTabs.tabs[tab_id].disabled;
    }
    // Could be used to select a tab from out of tabs
    selectTab(tab_id: number) {
        this.staticTabs.tabs[tab_id].active = true;
    }

}

