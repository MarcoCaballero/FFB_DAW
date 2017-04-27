
import { Team } from '../model/team.model';

export interface EgamesMatch {
  id: number;
  date: string;
  time: string;
  type: string;
  homeTeam: string;
  visitingTeam: string;
  quotaHomeVictory: number;
  quotaVisitingVictory: number;
  finished: boolean;
  teams: Team[];
  quotaHomeFirstBlood: number;
  quotaVisitingFirstBlood: number;
  winnerTeam: string;
  firstBloodTeam: string;
  winHome: boolean;
  firstBloodHome: boolean;
  winVisiting: boolean;
  firstBloodVisiting: boolean;
}
