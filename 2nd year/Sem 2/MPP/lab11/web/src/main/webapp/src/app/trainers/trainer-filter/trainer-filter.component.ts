import { Component, OnInit } from '@angular/core';
import {TrainersService} from "../shared/trainers.service";
import {Router} from "@angular/router";
import {Trainer} from "../shared/trainers.model";

@Component({
  selector: 'app-trainer-filter',
  templateUrl: './trainer-filter.component.html',
  styleUrls: ['./trainer-filter.component.css']
})
export class TrainerFilterComponent implements OnInit {
  errorMessage: string;
  trainers: Array<Trainer>;
  trainersWithAngular: Array<Trainer> = [];
  constructor(private trainerService: TrainersService,
              private router: Router) {
  }

  ngOnInit(): void {
  }

  filterTrainers(firstName: string) {
    this.trainerService.filterTrainers(firstName)
      .subscribe(
        trainers => this.trainers = trainers,
        error => this.errorMessage = <any>error
      );
  }

  filterTrainersAngular(value: string) {
    this.trainerService.getTrainers()
      .subscribe(trainers=>this.trainersWithAngular=trainers.filter(trainer=>trainer.firstName.includes(value)));
  }
}
