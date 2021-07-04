import { Component, OnInit } from '@angular/core';
import {TrainersService} from "../shared/trainers.service";
import {Location} from "@angular/common";
import {AwardsService} from "../../awards/shared/awards.service";

@Component({
  selector: 'app-trainer-update',
  templateUrl: './trainer-update.component.html',
  styleUrls: ['./trainer-update.component.css']
})
export class TrainerUpdateComponent implements OnInit {

  constructor(private trainerService: TrainersService,private awardService: AwardsService,
              private location: Location
  ) {
  }
  ngOnInit(): void {
  }

  updateTrainer(id:number, firstName: string, lastName: string, age: number, awardid:string) {
    if (id<0 || firstName=="" || lastName=="" || age==0){
      console.log("null credentials");
      return;
    }
    console.log("updating trainer", id, firstName, lastName, age, awardid);

    this.awardService.getAward(Number(awardid)).subscribe( award =>
      this.trainerService.update({
        id: Number(id),
        firstName: firstName,
        lastName: lastName,
        age: +age,
        award:award
      }).subscribe(trainer => console.log("updated trainer: ", trainer))
    )
    this.location.back(); // ...
  }

  Number(value: string) {
    return Number(value);
  }
}
