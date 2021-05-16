import {Injectable} from '@angular/core';

import {HttpClient} from "@angular/common/http";

import {SportiveTrainer} from "./sportivesTrainers.model";

import {Observable} from "rxjs";
import {map} from "rxjs/operators";
import {Sportive} from "../../sportives/shared/sportives.model";
import {Trainer} from "../../trainers/shared/trainers.model";


@Injectable()
export class SportiveTrainersService {
  private sportivesTrainersUrl = 'http://localhost:8080/api/sportivesTrainers';
  private pageSize = 3;

  constructor(private httpClient: HttpClient) {
  }

  getSportiveTrainersOnPage(pageNo: number):Observable<SportiveTrainer[]>{
    const url = `${this.sportivesTrainersUrl}/get-page/pageno=${pageNo},size=${this.pageSize}`;
    return this.httpClient.get<Array<SportiveTrainer>>(url);
  }

  getSportiveTrainers(): Observable<SportiveTrainer[]> {
    return this.httpClient
      .get<Array<SportiveTrainer>>(this.sportivesTrainersUrl);
  }

  getSportiveTrainer(id: number): Observable<SportiveTrainer> {
    return this.getSportiveTrainers()
      .pipe(
        map(sportivesTrainers => sportivesTrainers.find(sportivesTrainer => sportivesTrainer.id === id))
      );
  }

  filterSportiveTrainers(id: number) {
    const url=`${this.sportivesTrainersUrl}/allTrainersOfOneSportive/${id}`;
    return this.httpClient.get<Array<Trainer>>(url);
  }

  sortSportiveTrainers() {
    const url=`${this.sportivesTrainersUrl}/sort`;
    return this.httpClient.get<Array<SportiveTrainer>>(url);

  }

  getPageSize() {
    return this.pageSize;
  }
}
