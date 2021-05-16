import {Component, Input, OnInit} from '@angular/core';
import {ActivatedRoute, Params} from "@angular/router";
import {Location} from '@angular/common';
import {SportiveTrainer} from "../shared/sportivesTrainers.model";

import {switchMap} from "rxjs/operators";
import {SportiveTrainersService} from "../shared/sportivesTrainers.service";


@Component({
  selector: 'ubb-sportiveTrainer-detail',
  templateUrl: './sportiveTrainer-detail.component.html',
  styleUrls: ['./sportiveTrainer-detail.component.css'],
})

export class SportiveTrainerDetailComponent implements OnInit {

  @Input() sportiveTrainer: SportiveTrainer;

  constructor(private sportiveTrainerService: SportiveTrainersService,
              private route: ActivatedRoute,
              private location: Location) {
  }

  ngOnInit(): void {
    this.route.params
      .pipe(switchMap((params: Params) => this.sportiveTrainerService.getSportiveTrainer(+params['id'])))
      .subscribe(sportiveTrainer => this.sportiveTrainer = sportiveTrainer);
  }

  goBack(): void {
    this.location.back();
  }

  // save(): void {
  //   this.sportiveTrainerService.update(this.sportiveTrainer)
  //     .subscribe(_ => this.goBack());
  // }
}
