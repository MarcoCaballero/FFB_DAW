import { BetSportMatch } from './bet-sport-match.model';
import { BetEgamesMatch } from './bet-egames-match.model';

export interface BetTicket {
    id?: number;
    betMatchesList: BetSportMatch[];
    betEspMatchesList: BetEgamesMatch[];
    amount: number;
    potentialGain?: number;
    winned?: boolean;
    isLosed?: boolean;
    isUsed?: boolean;
    finished?: boolean;
}
