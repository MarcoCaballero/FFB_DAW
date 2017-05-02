import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

import { User } from '../../../model/user.model';
import { CreditCard } from '../../../model/creditCard.model';

import { AuthService } from '../../../services/auth.service'
import { UserService } from '../../../services/user.service';

@Component({
    moduleId: module.id,
    selector: 'ffbcomp-withdraw',
    templateUrl: 'withdraw.component.html',
    styleUrls: ['../../../../assets/css/styles.css']
})

export class WithdrawComponent implements OnInit {
    userLogged: User;
    amount = 0;
    cards: CreditCard[];

    title = 'Retirar Fondos';

    constructor(
        private authService: AuthService,
        private userService: UserService,
        private router: Router
    ) { }

    ngOnInit() {
        this.userLogged = this.authService.getUser();
        this.getUser(this.userLogged.id);
        this.getCards(this.userLogged);
    }

    getUser(id: number) {
        this.userService.getUser(id)
            .then(response => this.userLogged = response);
    }

    getCards(user: User) {
        this.userService.getCards(user.id)
            .then(response => this.cards = response);
    }

    creditLess(cardNumber: string, amount: number) {
        this.userService.creditCardLess(cardNumber, amount)
            .then(() => this.router.navigate(['/user/myaccount']));
    }

}

