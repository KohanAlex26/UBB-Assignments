import { Component, OnInit } from '@angular/core';
import {TrainersService} from "../shared/trainers.service";
import {Location} from "@angular/common";
@Component({
  selector: 'app-trainer-delete',
  templateUrl: './trainers-delete.component.html',
  styleUrls: ['./trainers-delete.component.css']
})
export class TrainerDeleteComponent implements OnInit {

  constructor(private trainerService: TrainersService,
              private location: Location
  ) {
  }

  ngOnInit(): void {
  }


  Number(value: string) {
    return Number(value);
  }

  removeTrainer(id: number) {
    console.log("deleting trainer ", id);

    this.trainerService.deleteTrainer(id)
      .subscribe(trainer => console.log("deleted trainer: ", trainer));

    this.location.back(); // ...
  }
}

