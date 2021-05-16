import {Injectable} from '@angular/core';

import {HttpClient} from "@angular/common/http";

import {Sportive} from "./sportives.model";

import {Observable} from "rxjs";
import {map} from "rxjs/operators";


@Injectable()
export class SportivesService {
  private sportivesUrl = 'http://localhost:8080/api/sportives';
  private pageSize = 3;

  constructor(private httpClient: HttpClient) {
  }

  getSportivesOnPage(pageNo: number):Observable<Sportive[]>{
    const url = `${this.sportivesUrl}/get-page/pageno=${pageNo},size=${this.pageSize}`;
    return this.httpClient.get<Array<Sportive>>(url);
  }

  getSportives(): Observable<Sportive[]> {
    return this.httpClient
      .get<Array<Sportive>>(this.sportivesUrl);
  }

  getSportive(id: number): Observable<Sportive> {
    return this.getSportives()
      .pipe(
        map(sportives => sportives.find(sportive => sportive.id === id))
      );
  }

  saveSportive(sportive: Sportive): Observable<Sportive> {
    console.log("saveSportive", sportive);

    return this.httpClient
      .post<Sportive>(this.sportivesUrl, sportive);
  }

  update(sportive:Sportive): Observable<Sportive> {
    console.log("updateSportive", sportive);

    const url = `${this.sportivesUrl}/${sportive.id}`;
    return this.httpClient
      .put<Sportive>(url, sportive);
  }

  deleteSportive(id: number): Observable<any> {
    const url = `${this.sportivesUrl}/${id}`;
    return this.httpClient
      .delete(url);
  }

  filterSportives(firstName: string) {
    const url=`${this.sportivesUrl}/filterFirstName/${firstName}`;
    return this.httpClient.get<Array<Sportive>>(url);
  }

  sortSportives() {
    const url=`${this.sportivesUrl}/sort`;
    return this.httpClient.get<Array<Sportive>>(url);

  }

  getPageSize() {
    return this.pageSize;
  }
}
