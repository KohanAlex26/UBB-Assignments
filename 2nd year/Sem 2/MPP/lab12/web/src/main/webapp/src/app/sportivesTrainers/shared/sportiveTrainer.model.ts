import {Sportive} from "../../sportives/shared/sportives.model";
import {Trainer} from "../../trainers/shared/trainers.model";

export class SportiveTrainer {
  id;
  cost: number;
  trainingType:string;
  sportive:Sportive;
  trainer:Trainer;

  constructor(cost: number = 0, trainingType: string = "", sportive: Sportive = null, trainer: Trainer = null) {
    this.cost = cost;
    this.trainingType = trainingType;
    this.sportive = sportive;
    this.trainer = trainer;
    this.id = {
      sportiveId: sportive.id,
      trainerId: trainer.id
    }
  }
}

export class SportiveTrainerAddDTO {
  cost: number;
  trainingType:string;
  sportiveId:number;
  trainerId:number;


  constructor(cost: number, trainingType: string, sportiveId: number, trainerId: number) {
    this.cost = cost;
    this.trainingType = trainingType;
    this.sportiveId = sportiveId;
    this.trainerId = trainerId;
  }
}
