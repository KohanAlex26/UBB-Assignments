import {Injectable} from '@angular/core';

import {HttpClient} from "@angular/common/http";

import {Award} from "./awards.model";

import {Observable} from "rxjs";
import {map} from "rxjs/operators";
import {Team} from "../../teams/shared/teams.model";


@Injectable()
export class AwardsService {
  private awardsUrl = 'http://localhost:8080/api/awards';
  private pageSize = 3;

  constructor(private httpClient: HttpClient) {
  }

  getAwardsOnPage(pageNo: number):Observable<Award[]>{
    const url = `${this.awardsUrl}/get-page/pageno=${pageNo},size=${this.pageSize}`;
    return this.httpClient.get<Array<Award>>(url);
  }

  getAwards(): Observable<Award[]> {
    return this.httpClient
      .get<Array<Award>>(this.awardsUrl);
  }

  getAward(id: number): Observable<Award> {
    return this.getAwards()
      .pipe(
        map(awards => awards.find(award => award.id === id))
      );
  }

  saveAward(award: Award): Observable<Award> {
    console.log("saveAward", award);

    return this.httpClient
      .post<Award>(this.awardsUrl, award);
  }

  update(award:Award): Observable<Award> {
    console.log("updateAward", award);

    const url = `${this.awardsUrl}/${award.id}`;
    return this.httpClient
      .put<Award>(url, award);
  }

  deleteAward(id: number): Observable<any> {
    const url = `${this.awardsUrl}/${id}`;
    return this.httpClient
      .delete(url);
  }

  // filterAwards(firstName: string) {
  //   const url=`${this.awardsUrl}/filterFirstName/${firstName}`;
  //   return this.httpClient.get<Array<Award>>(url);
  // }
  //
  // sortAwards() {
  //   const url=`${this.awardsUrl}/sort`;
  //   return this.httpClient.get<Array<Award>>(url);
  //
  // }

  getPageSize() {
    return this.pageSize;
  }
}
