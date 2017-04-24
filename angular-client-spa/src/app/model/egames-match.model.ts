
import { Team } from '../model/team.model';

export interface EgamesMatch {
  id: number;
  date: string;
  time: string;
  type: string;
  homeTeam: string;
  visitingTeam: string;
  quotaHomeVictory: string;
  quotaVisitingVictory: string;
  isFinished: boolean;
  teams: Team[];
  quotaDraw: number;
  quotaHomeFirstBlood: number;
  quotaVisitingFirstBlood: number;
  winnerTeam: string;
  firstBloodTeam: string;
  winHome: boolean;
  firstBloodHome: boolean;
  winVisiting: boolean;
  firstBloodVisiting: boolean;
}
