import {Component, OnInit} from '@angular/core';
import {SportiveTrainerService} from "../shared/sportiveTrainer.service";
import {Location} from "@angular/common";
import {SportivesService} from "../../sportives/shared/sportives.service";
import {TrainersService} from "../../trainers/shared/trainers.service";

@Component({
  selector: 'app-sportiveTrainer-new',
  templateUrl: './sportiveTrainer-new.component.html',
  styleUrls: ['./sportiveTrainer-new.component.css']
})
export class SportiveTrainerNewComponent implements OnInit {

  constructor(private sportiveTrainerService: SportiveTrainerService,private sportiveService: SportivesService,private trainerService: TrainersService,
              private location: Location
  ) {
  }

  ngOnInit(): void {
  }

  saveSportiveTrainer(cost: string, trainingType:string, sportiveId:string, trainerId:string) {
    if (cost=="" || trainingType=="" || sportiveId=="" || trainerId==""){
      console.log("null credentials");
      return;
    }
    // var regex = /[a-z]/g;
    // if (!regex.test(sportiveTrainerName)){
    //   console.log("sportiveTrainerName doesn't match the regex");
    //   return;
    // }
    console.log("saving sportiveTrainer", cost, trainingType, sportiveId,trainerId);

    // this.sportiveTrainerService.saveSportiveTrainer({
    //   // id: Number(id),
    //   cost: +cost,
    //   trainingType: trainingType,
    //   sportive:+sportiveId,
    //   trainer:+trainerId,
    // }).subscribe(sportiveTrainer => console.log("saved sportiveTrainer: ", sportiveTrainer))

    this.trainerService.getTrainer(Number(trainerId)).subscribe(trainer=>
    this.sportiveService.getSportive(Number(sportiveId)).subscribe(sportive=>
    this.sportiveTrainerService.saveSportiveTrainer(
      Number(cost),
      trainingType,
      Number(sportiveId),
      Number(trainerId),
    ).subscribe(sportiveTrainer => console.log("saved sportiveTrainer: ", sportiveTrainer))
  ))

    this.location.back(); // ...
  }
}
