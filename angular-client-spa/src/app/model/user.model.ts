
export interface User {
  id?: number;
  email: string;
  roles: string[];
  name?: string;
  surname: string;
  secondSurname: string;
  dni: string;
  telephone: string;
  passwordHash?: string;
  country?: string;
  city?: string;
  location?: string[];
  photoUrl?: string;
}
