import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Location } from '@angular/common';

import { UserService } from '../../../services/user.service';

import { CreditCard } from '../../../model/creditCard.model';

@Component({
    moduleId: module.id,
    selector: 'ffbcomp-addcredit',
    templateUrl: 'addcredit.component.html',
    styleUrls: ['../../../../assets/css/styles.css']
})

export class AddcreditComponent implements OnInit {
    public radioModel = 'visa';
    selectedCard: boolean;
    creditCard: CreditCard;

    title = 'Añadir Fondos';

    constructor(
        private userService: UserService,
        private router: Router,
        private location: Location
    ) { }

    ngOnInit() { }

    creditPlus(card: CreditCard) {
        this.userService.creditCardPlus(card)
            .then(() => this.router.navigate(['/user/myaccount']));
    }

}
