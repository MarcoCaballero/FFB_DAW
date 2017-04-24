
import { SportMatch } from '../model/sport-match.model';

export interface Team {
  id: number;
  name: string;
  type: string;
  coach: string;
  country: string;
  city: string;
  slogan?: string;
  stadium?: string;
  president?: string;
  leagues?: number;
  cups?: number;
  champions?: number;
  twitter?: string;
  facebook?: string;
  google?: string;
  sponsor?: string;
  logo_image?: string;
  stadium_image?: string;
}
