import { SportMatch } from './sport-match.model';

export interface BetSportMatch {
    id?: number;
    match: SportMatch;
    localSelected: boolean;
    drawSelected?: boolean;
    visitingSelected: boolean;
    selectedNamequota?: string;
    selectedQuota?: number;
}
