
import { Team } from '../model/team.model';

export interface SportMatch {
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
  homePoints: number;
  visitingPoints: number;
}
