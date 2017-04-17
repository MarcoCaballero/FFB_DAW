import { Component, OnInit } from '@angular/core';
import { TabsetComponent } from 'ngx-bootstrap';

@Component({
    moduleId: module.id,
    selector: 'ffbcomp-myaccount',
    templateUrl: 'myaccount.component.html',
    styleUrls: ['../../../assets/css/styles.css', '../../../assets/css/cardstyle.css', '../../../assets/css/cardstyletop.css']
})

export class MyAccountComponent implements OnInit {
    // Public fields
    public isCollapsed: boolean = false;

    title = 'MyAccount';

    constructor() { }

    ngOnInit() { }

    public collapsed(event: any): void {
        console.log(event);
    }

    public expanded(event: any): void {
        console.log(event);
    }

}

