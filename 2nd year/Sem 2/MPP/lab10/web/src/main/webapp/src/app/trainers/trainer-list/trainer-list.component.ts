import {Component, OnInit} from '@angular/core';
import {Trainer} from "../shared/trainers.model";
import {TrainersService} from "../shared/trainers.service";
import {Router} from "@angular/router";
import {Observable} from "rxjs";


@Component({
  moduleId: module.id,
  selector: 'ubb-trainer-list',
  templateUrl: './trainer-list.component.html',
  styleUrls: ['./trainer-list.component.css'],
})
export class TrainerListComponent implements OnInit {
  errorMessage: string;
  trainers: Array<Trainer>;
  selectedTrainer: Trainer;
  currentPage: number;
  totalSize:number;
  acceptableSize:number;

  constructor(private trainerService: TrainersService,
              private router: Router) {

  }

  ngOnInit(): void {
    this.currentPage=0;
    this.trainerService.getTrainers()
      .subscribe(
        trainers=>this.totalSize=trainers.length,
        error=>this.errorMessage=<any>error);
    this.getTrainersPaginated();
  }


  buttonsInteraction(){
    this.acceptableSize=Math.ceil(this.totalSize/this.trainerService.getPageSize().valueOf());
    if (this.currentPage==0)
      document.getElementById("decrease-btn").hidden=true;
    else if (this.currentPage==this.acceptableSize-1)
      document.getElementById("increase-btn").hidden=true;
  }


  getTrainersPaginated(){
    this.buttonsInteraction();
    this.trainerService.getTrainersOnPage(this.currentPage)
      .subscribe(trainers=>this.trainers = trainers,
                error=>this.errorMessage=<any>error);
  }

  getTrainers() {
    this.trainerService.getTrainers()
      .subscribe(
        trainers => this.trainers = trainers,
        error => this.errorMessage = <any>error
      );
  }

  onSelect(trainer: Trainer): void {
    this.selectedTrainer = trainer;
  }

  gotoDetail(): void {
    this.router.navigate(['/trainer/detail', this.selectedTrainer.id]);
  }

  deleteTrainer(trainer: Trainer) {
    console.log("deleting trainer: ", trainer);

    this.trainerService.deleteTrainer(trainer.id)
      .subscribe(_ => {
        console.log("trainer deleted");

        this.trainers = this.trainers
          .filter(s => s.id !== trainer.id);
      });
  }

  increasePageNo() {
    this.acceptableSize=Math.ceil(this.totalSize/this.trainerService.getPageSize().valueOf());
    if (this.currentPage<this.acceptableSize-1){
      this.currentPage++;
      document.getElementById("decrease-btn").hidden=false;
      this.getTrainersPaginated();
    }
  }

  decreasePageNo() {
    if(this.currentPage>0) {
      this.currentPage--;
      document.getElementById("increase-btn").hidden=false;
      this.getTrainersPaginated();
    }
  }
}
