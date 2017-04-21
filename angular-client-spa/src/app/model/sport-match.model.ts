
import { SportTeam } from '../model/sport-team.model';

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
  teams: SportTeam[];
  quotaDraw: number;
  homePoints: number;
  visitingPoints: number;
}
