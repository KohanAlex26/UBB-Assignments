import { Component, OnInit } from '@angular/core';
import {SportivesService} from "../shared/sportives.service";
import {Router} from "@angular/router";
import {Sportive} from "../shared/sportives.model";

@Component({
  selector: 'app-sportive-filter',
  templateUrl: './sportive-filter.component.html',
  styleUrls: ['./sportive-filter.component.css']
})
export class SportiveFilterComponent implements OnInit {
  errorMessage: string;
  sportives: Array<Sportive>;
  sportivesWithAngular: Array<Sportive> = [];
  constructor(private sportiveService: SportivesService,
              private router: Router) {
  }

  ngOnInit(): void {
  }

  filterSportives(firstName: string) {
    this.sportiveService.filterSportives(firstName)
      .subscribe(
        sportives => this.sportives = sportives,
        error => this.errorMessage = <any>error
      );
  }

  filterSportivesAngular(value: string) {
    this.sportiveService.getSportives()
      .subscribe(sportives=>this.sportivesWithAngular=sportives.filter(sportive=>sportive.firstName.includes(value)));
  }
}
