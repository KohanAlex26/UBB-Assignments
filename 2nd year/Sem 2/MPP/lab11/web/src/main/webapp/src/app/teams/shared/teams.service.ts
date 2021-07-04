import {Injectable} from '@angular/core';

import {HttpClient} from "@angular/common/http";

import {Team} from "./teams.model";

import {Observable} from "rxjs";
import {map} from "rxjs/operators";


@Injectable()
export class TeamsService {
  private teamsUrl = 'http://localhost:8080/api/teams';
  private pageSize = 3;

  constructor(private httpClient: HttpClient) {
  }

  getTeamsOnPage(pageNo: number):Observable<Team[]>{
    const url = `${this.teamsUrl}/get-page/pageno=${pageNo},size=${this.pageSize}`;
    return this.httpClient.get<Array<Team>>(url);
  }

  getTeams(): Observable<Team[]> {
    return this.httpClient
      .get<Array<Team>>(this.teamsUrl);
  }

  getTeam(id: number): Observable<Team> {
    return this.getTeams()
      .pipe(
        map(teams => teams.find(team => team.id === id))
      );
  }

  saveTeam(team: Team): Observable<Team> {
    console.log("saveTeam", team);

    return this.httpClient
      .post<Team>(this.teamsUrl, team);
  }

  update(team:Team): Observable<Team> {
    console.log("updateTeam", team);

    const url = `${this.teamsUrl}/${team.id}`;
    return this.httpClient
      .put<Team>(url, team);
  }

  deleteTeam(id: number): Observable<any> {
    const url = `${this.teamsUrl}/${id}`;
    return this.httpClient
      .delete(url);
  }

  filterTeams(teamName: string) {
    const url=`${this.teamsUrl}/filterByName/${teamName}`;
    return this.httpClient.get<Array<Team>>(url);
  }

  sortTeams() {
    const url=`${this.teamsUrl}/sort`;
    return this.httpClient.get<Array<Team>>(url);

  }

  getPageSize() {
    return this.pageSize;
  }
}
