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
    title = 'HOME';

    @ViewChild('staticTabs') staticTabs: TabsetComponent;



    constructor() { }

    ngOnInit() { }

    public alertMe(): void {
        setTimeout(function (): void {
            alert('You\'ve selected the alert tab!');
        });
    }

    selectTab(tab_id: number) {
        this.staticTabs.tabs[tab_id].active = true;
    }

    disableEnable() {
        this.staticTabs.tabs[2].disabled = !this.staticTabs.tabs[2].disabled
    }
}
