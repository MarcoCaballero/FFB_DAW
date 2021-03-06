import {environment} from 'environments/environment';
// Base URL
export const baseUrl = environment.apiUrl;

// Login Service URL'S
export const loginUrl = baseUrl + 'logIn';
export const logoutUrl = baseUrl + 'logOut';

// Bet Service URL'S
export const betUrl = baseUrl + 'bet/';
export const betMatchhUrl = baseUrl + 'bet/match/';

// User Module Url
export const userUrl = baseUrl + 'user/';
export const userCards = baseUrl + 'user/creditCards/';
export const userAddCreditUrl = baseUrl + 'user/creditCardPlus/';
export const userWithDrawCreditUrl = baseUrl + 'user/creditCardLess/';
export const userTickets = baseUrl + 'user/tickets/';
export const userStorageUrl = baseUrl + 'storage/';

// Admin Module
// Teams
export const teamUrl = baseUrl + 'teams/';

export const teamSportUrl = baseUrl + 'teams/sports/';
export const teamEgamesUrl = baseUrl + 'teams/egames/';
// Matches
export const matchUrl = baseUrl + 'matches/';

export const matchSportUrl = baseUrl + 'matches/sports/';
export const matchFootballUrl = baseUrl + 'matches/sports/football/';
export const matchBasketlUrl = baseUrl + 'matches/sports/basketball/';

export const matchEgamesUrl = baseUrl + 'matches/egames/';
export const matchLolUrl = baseUrl + 'matches/egames/lol/';
export const matchCsUrl = baseUrl + 'matches/egames/cs/';
