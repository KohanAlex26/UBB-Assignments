import {Component, Input, OnInit} from '@angular/core';
import {TrainersService} from "../shared/trainers.service";
import {ActivatedRoute, Params} from "@angular/router";
import {Location} from '@angular/common';
import {Trainer} from "../shared/trainers.model";

import {switchMap} from "rxjs/operators";


@Component({
  selector: 'ubb-trainer-detail',
  templateUrl: './trainer-detail.component.html',
  styleUrls: ['./trainer-detail.component.css'],
})

export class TrainerDetailComponent implements OnInit {

  @Input() trainer: Trainer;

  constructor(private trainerService: TrainersService,
              private route: ActivatedRoute,
              private location: Location) {
  }

  ngOnInit(): void {
    this.route.params
      .pipe(switchMap((params: Params) => this.trainerService.getTrainer(+params['id'])))
      .subscribe(trainer => this.trainer = trainer);
  }

  goBack(): void {
    this.location.back();
  }

  save(): void {
    this.trainerService.update(this.trainer)
      .subscribe(_ => this.goBack());
  }
}
