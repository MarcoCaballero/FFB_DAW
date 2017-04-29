import { CreditCard } from '../model/creditCard.model';

export interface User {
  id: number;
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
  men?: boolean;
  photoUrl?: string;
  cards?: CreditCard[];
}
