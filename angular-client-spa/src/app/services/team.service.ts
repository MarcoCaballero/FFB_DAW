import { Injectable } from '@angular/core';
import { Http, Headers, RequestOptions } from '@angular/http';

export interface Team {
    id?: number;
    name: string;
    type: string;
    coach: string;
    country: string;
    city: string;
    slogan?: string;
    stadium?: string;
    president?: string;
    leagues?: number;
    cups?: number;
    champions?: number;
    twitter?: string;
    facebook?: string;
    google?: string;
    sponsor?: string;
}

@Injectable()
export class TeamService {
    team: Team;

    constructor(private http: Http) { }

    // Hay que hacer el servicio
}
