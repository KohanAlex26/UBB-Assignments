import { Component, OnInit } from '@angular/core';
import {TrainersService} from "../shared/trainers.service";
import {Location} from "@angular/common";

@Component({
  selector: 'app-trainer-update',
  templateUrl: './trainer-update.component.html',
  styleUrls: ['./trainer-update.component.css']
})
export class TrainerUpdateComponent implements OnInit {

  constructor(private trainerService: TrainersService,
              private location: Location
  ) {
  }
  ngOnInit(): void {
  }

  updateTrainer(id:number, firstName: string, lastName: string, age: number) {
    if (id==0 || firstName=="" || lastName=="" || age==0){
      console.log("null credentials");
      return;
    }
    console.log("updating trainer", id, firstName, lastName, age);

    this.trainerService.update({
      id: +id,
      firstName:firstName,
      lastName:lastName,
      age: +age,
    })
      .subscribe(trainer => console.log("updated trainer: ", trainer));

    this.location.back(); // ...
  }

  Number(value: string) {
    return Number(value);
  }
}
