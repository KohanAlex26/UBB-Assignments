import { Component, OnInit } from '@angular/core';
import {SportiveTrainer} from "../shared/sportivesTrainers.model";
import {SportiveTrainersService} from "../shared/sportivesTrainers.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-sportiveTrainer-sort',
  templateUrl: './sportiveTrainer-sort.component.html',
  styleUrls: ['./sportiveTrainer-sort.component.css']
})
export class SportiveTrainerSortComponent implements OnInit {
  errorMessage: string;
  sportiveTrainers: Array<SportiveTrainer>;

  constructor(private sportiveTrainerService: SportiveTrainersService,
              private router: Router) {
    this.sportiveTrainerService.sortSportiveTrainers()
      .subscribe(
        sportiveTrainers => this.sportiveTrainers = sportiveTrainers,
        error => this.errorMessage = <any>error
      );
  }

  ngOnInit(): void {
  }

}
