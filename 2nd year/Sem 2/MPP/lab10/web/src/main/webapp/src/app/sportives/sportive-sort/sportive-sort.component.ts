import { Component, OnInit } from '@angular/core';
import {Sportive} from "../shared/sportives.model";
import {SportivesService} from "../shared/sportives.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-sportive-sort',
  templateUrl: './sportive-sort.component.html',
  styleUrls: ['./sportive-sort.component.css']
})
export class SportiveSortComponent implements OnInit {
  errorMessage: string;
  sportives: Array<Sportive>;

  constructor(private sportiveService: SportivesService,
              private router: Router) {
    this.sportiveService.sortSportives()
      .subscribe(
        sportives => this.sportives = sportives,
        error => this.errorMessage = <any>error
      );
  }

  ngOnInit(): void {
  }

}
