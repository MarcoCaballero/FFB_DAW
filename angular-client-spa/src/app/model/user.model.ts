
export interface User {
  id?: number;
  email: string;
  roles: string[];
  name: string;
  surname: string;
  secondSurname: string;
  dni: string;
  telephone: string;
  password?: string;
  country?: string;
  city?: string;
  location?: string[];
  sex?: boolean;
  photoUrl?: string;
}
