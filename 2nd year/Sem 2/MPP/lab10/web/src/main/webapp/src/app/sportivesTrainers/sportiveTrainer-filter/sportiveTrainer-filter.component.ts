import { Component, OnInit } from '@angular/core';
import {SportiveTrainersService} from "../shared/sportivesTrainers.service";
import {Router} from "@angular/router";
import {SportiveTrainer} from "../shared/sportivesTrainers.model";
import {Sportive} from "../../sportives/shared/sportives.model";
import {Trainer} from "../../trainers/shared/trainers.model";

@Component({
  selector: 'app-sportiveTrainer-filter',
  templateUrl: './sportiveTrainer-filter.component.html',
  styleUrls: ['./sportiveTrainer-filter.component.css']
})
export class SportiveTrainerFilterComponent implements OnInit {
  errorMessage: string;
  sportiveTrainers: Array<SportiveTrainer>;
  sportiveTrainersWithAngular: Array<SportiveTrainer> = [];
  trainers: Array<Trainer>;
  trainersWithAngular: Array<Trainer> = [];
  constructor(private sportiveTrainerService: SportiveTrainersService,
              private router: Router) {
  }

  ngOnInit(): void {
  }

  filterSportiveTrainers(id: string) {
    this.sportiveTrainerService.filterSportiveTrainers(Number(id))
      .subscribe(
        trainers => this.trainers = trainers,
        error => this.errorMessage = <any>error
      );
  }

  // filterSportiveTrainersAngular(id:number) {
  //   this.sportiveTrainerService.getSportiveTrainers()
  //     .subscribe(sportivesTrainers=>this.trainersWithAngular=sportivesTrainers.filter(sportiveTrainer=>sportiveTrainer.sportiveID==id).map(sp->sp.get));
  // }
}
