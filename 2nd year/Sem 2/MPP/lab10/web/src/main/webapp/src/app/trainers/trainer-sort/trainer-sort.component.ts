import { Component, OnInit } from '@angular/core';
import {Trainer} from "../shared/trainers.model";
import {TrainersService} from "../shared/trainers.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-trainer-sort',
  templateUrl: './trainer-sort.component.html',
  styleUrls: ['./trainer-sort.component.css']
})
export class TrainerSortComponent implements OnInit {
  errorMessage: string;
  trainers: Array<Trainer>;

  constructor(private trainerService: TrainersService,
              private router: Router) {
    this.trainerService.sortTrainers()
      .subscribe(
        trainers => this.trainers = trainers,
        error => this.errorMessage = <any>error
      );
  }

  ngOnInit(): void {
  }

}
