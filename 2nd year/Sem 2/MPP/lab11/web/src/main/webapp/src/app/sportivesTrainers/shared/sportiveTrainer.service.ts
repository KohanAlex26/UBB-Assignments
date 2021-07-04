import {Injectable} from '@angular/core';

import {HttpClient} from "@angular/common/http";

import {SportiveTrainer, SportiveTrainerAddDTO} from "./sportiveTrainer.model";

import {Observable} from "rxjs";
import {map} from "rxjs/operators";
import {Sportive} from "../../sportives/shared/sportives.model";
import {Trainer} from "../../trainers/shared/trainers.model";


@Injectable()
export class SportiveTrainerService {
  private sportiveTrainersUrl = 'http://localhost:8080/api/sportiveTrainer';
  private pageSize = 3;

  constructor(private httpClient: HttpClient) {
  }

  // getSportiveTrainersOnPage(pageNo: number):Observable<SportiveTrainer[]>{
  //   const url = `${this.sportiveTrainersUrl}/get-page/pageno=${pageNo},size=${this.pageSize}`;
  //   return this.httpClient.get<Array<SportiveTrainer>>(url);
  // }
  //
  // getSportiveTrainers(): Observable<SportiveTrainer[]> {
  //   return this.httpClient
  //     .get<Array<SportiveTrainer>>(this.sportiveTrainersUrl);
  // }
  //
  // getSportiveTrainer(id: number): Observable<SportiveTrainer> {
  //   return this.getSportiveTrainers()
  //     .pipe(
  //       map(sportiveTrainers => sportiveTrainers.find(sportiveTrainer => sportiveTrainer.id === id))
  //     );
  // }

  saveSportiveTrainer(cost: number,
                      trainingType:string,
                      sportiveId:number,
                      trainerId:number,): Observable<any> {
    // console.log("saveSportiveTrainer", sportiveTrainer);

    return this.httpClient
      .post(this.sportiveTrainersUrl, new SportiveTrainerAddDTO(cost,trainingType,sportiveId,trainerId));
  }

  // update(sportiveTrainer:SportiveTrainer): Observable<SportiveTrainer> {
  //   console.log("updateSportiveTrainer", sportiveTrainer);
  //
  //   const url = `${this.sportiveTrainersUrl}/${sportiveTrainer.id}`;
  //   return this.httpClient
  //     .put<SportiveTrainer>(url, sportiveTrainer);
  // }
  //
  // deleteSportiveTrainer(id: number): Observable<any> {
  //   const url = `${this.sportiveTrainersUrl}/${id}`;
  //   return this.httpClient
  //     .delete(url);
  // }
  //
  // filterSportiveTrainers(sportiveTrainerName: string) {
  //   const url=`${this.sportiveTrainersUrl}/filterByName/${sportiveTrainerName}`;
  //   return this.httpClient.get<Array<SportiveTrainer>>(url);
  // }
  //
  // sortSportiveTrainers() {
  //   const url=`${this.sportiveTrainersUrl}/sort`;
  //   return this.httpClient.get<Array<SportiveTrainer>>(url);
  //
  // }
  //
  // getPageSize() {
  //   return this.pageSize;
  // }
}
