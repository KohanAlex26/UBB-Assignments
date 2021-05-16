import {Injectable} from '@angular/core';

import {HttpClient} from "@angular/common/http";


import {Observable} from "rxjs";
import {map} from "rxjs/operators";
import {Sportive} from "./sportive.model";


@Injectable()
export class SportiveService {
  private sportivesUrl = 'http://localhost:8080/api/sportives';

  constructor(private httpClient: HttpClient) {
  }

  getSportives(): Observable<Sportive[]> {
    return this.httpClient
      .get<Array<Sportive>>(this.sportivesUrl);
  }

  saveSportive(sportive: Sportive): Observable<Sportive> {
    return this.httpClient
      .post<Sportive>(this.sportivesUrl, sportive);
  }

  // getStudent(id: number): Observable<Student> {
  //   return this.getStudents()
  //     .pipe(
  //       map(students => students.find(student => student.id === id))
  //     );
  // }
  //
  // update(student): Observable<Student> {
  //   const url = `${this.sportivesUrl}/${student.id}`;
  //   return this.httpClient
  //     .put<Student>(url, student);
  // }

}
