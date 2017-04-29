import { Component, OnInit } from '@angular/core';

import { UserService } from '../../../services/user.service';

import { CreditCard } from '../../../model/creditCard.model';

@Component({
    moduleId: module.id,
    selector: 'ffbcomp-addcredit',
    templateUrl: 'addcredit.component.html',
    styleUrls: ['../../../../assets/css/styles.css']
})

export class AddcreditComponent implements OnInit {
    public radioModel: string = 'visa';
    selectedCard: boolean;
    creditCard: CreditCard;

    title = 'Añadir Fondos';

    constructor(
        private userService: UserService
    ) { }

    ngOnInit() { }

    creditPlus(card: CreditCard) {
        this.userService.creditCardPlus(card);
    }

}
