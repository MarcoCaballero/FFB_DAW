
import { SportMatch } from '../model/sport-match.model';

export interface SportTeam {
  id: number;
  name: string;
  type: string;
  coach: string;
  country: string;
  city: string;
  matches: SportMatch[];
  slogan: string;
  stadium: string;
  president: string;
  leagues: number;
  cups: number;
  champions: number;
  stadium_image: string;
  logo_image: string;
  twitter_Uri: string;
  facebook_Uri: string;
  google_Uri: string;
}