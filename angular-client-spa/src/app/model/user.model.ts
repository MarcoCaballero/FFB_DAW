import { CreditCard } from '../model/creditCard.model';
import { BetTicket } from '../model/bet-ticket.model';

export interface User {
  id: number;
  credit: number;
  email: string;
  roles: string[];
  name: string;
  password: string;
  surname?: string;
  secondSurname?: string;
  dni?: string;
  telephone?: string;
  country?: string;
  city?: string;
  location?: string;
  men: boolean;
  photoSelected: boolean;
  photoUrl?: string;
  cards?: CreditCard[];
  betTickets?: BetTicket[];
}
