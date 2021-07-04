import {Injectable} from '@angular/core';

import {HttpClient} from "@angular/common/http";

import {Trainer} from "./trainers.model";

import {Observable} from "rxjs";
import {map} from "rxjs/operators";


@Injectable()
export class TrainersService {
  private trainersUrl = 'http://localhost:8080/api/trainers';
  private pageSize = 3;

  constructor(private httpClient: HttpClient) {
  }

  getTrainersOnPage(pageNo: number):Observable<Trainer[]>{
    const url = `${this.trainersUrl}/get-page/pageno=${pageNo},size=${this.pageSize}`;
    return this.httpClient.get<Array<Trainer>>(url);
  }

  getTrainers(): Observable<Trainer[]> {
    return this.httpClient
      .get<Array<Trainer>>(this.trainersUrl);
  }

  getTrainer(id: number): Observable<Trainer> {
    return this.getTrainers()
      .pipe(
        map(trainers => trainers.find(trainer => trainer.id === id))
      );
  }

  saveTrainer(trainer: Trainer): Observable<Trainer> {
    console.log("saveTrainer", trainer);

    return this.httpClient
      .post<Trainer>(this.trainersUrl, trainer);
  }

  update(trainer:Trainer): Observable<Trainer> {
    console.log("updateTrainer", trainer);

    const url = `${this.trainersUrl}/${trainer.id}`;
    return this.httpClient
      .put<Trainer>(url, trainer);
  }

  deleteTrainer(id: number): Observable<any> {
    const url = `${this.trainersUrl}/${id}`;
    return this.httpClient
      .delete(url);
  }

  filterTrainers(firstName: string) {
    const url=`${this.trainersUrl}/filterFirstName/${firstName}`;
    return this.httpClient.get<Array<Trainer>>(url);
  }

  sortTrainers() {
    const url=`${this.trainersUrl}/sort`;
    return this.httpClient.get<Array<Trainer>>(url);

  }

  getPageSize() {
    return this.pageSize;
  }
}
