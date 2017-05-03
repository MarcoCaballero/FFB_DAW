import { EgamesMatch } from './egames-match.model';

export interface BetEgamesMatch {
    id?: number;
    match: EgamesMatch;
    localSelected: boolean;
    drawSelected?: boolean;
    visitingSelected: boolean;
    localFirstBloodSelected: boolean;
    visitingFirstBloodSelected: boolean;
    selectedNamequota?: string;
    selectedQuota?: number;
    selectedFirstBloodNamequota?: string;
    selectedFirstBloodQuota?: number;

}
