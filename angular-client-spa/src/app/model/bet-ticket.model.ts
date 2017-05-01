import { BetSportMatch } from './bet-sport-match.model';

export interface BetTicket {
    id?: number;
    betMatchesList: BetSportMatch[];
    // betMatchesList: BetEgamesMatch[];
    amount: number;
    potentialGain?: number;
    winned?: boolean;
    isLosed?: boolean;
    isUsed?: boolean;
    finished?: boolean;
}
