import {Component, OnInit} from '@angular/core';
import {SportiveTrainer} from "../shared/sportivesTrainers.model";
import {SportiveTrainersService} from "../shared/sportivesTrainers.service";
import {Router} from "@angular/router";
import {Observable} from "rxjs";


@Component({
  moduleId: module.id,
  selector: 'ubb-sportiveTrainer-list',
  templateUrl: './sportiveTrainer-list.component.html',
  styleUrls: ['./sportiveTrainer-list.component.css'],
})
export class SportiveTrainerListComponent implements OnInit {
  errorMessage: string;
  sportiveTrainers: Array<SportiveTrainer>;
  selectedSportiveTrainer: SportiveTrainer;
  currentPage: number;
  totalSize:number;
  acceptableSize:number;

  constructor(private sportiveTrainerService: SportiveTrainersService,
              private router: Router) {

  }

  ngOnInit(): void {
    this.currentPage=0;
    this.sportiveTrainerService.getSportiveTrainers()
      .subscribe(
        sportiveTrainers=>this.totalSize=sportiveTrainers.length,
        error=>this.errorMessage=<any>error);
    this.getSportiveTrainersPaginated();
  }


  buttonsInteraction(){
    this.acceptableSize=Math.ceil(this.totalSize/this.sportiveTrainerService.getPageSize().valueOf());
    if (this.currentPage==0)
      document.getElementById("decrease-btn").hidden=true;
    else if (this.currentPage==this.acceptableSize-1)
      document.getElementById("increase-btn").hidden=true;
  }


  getSportiveTrainersPaginated(){
    this.buttonsInteraction();
    this.sportiveTrainerService.getSportiveTrainersOnPage(this.currentPage)
      .subscribe(sportiveTrainers=>this.sportiveTrainers = sportiveTrainers,
                error=>this.errorMessage=<any>error);
  }

  getSportiveTrainers() {
    this.sportiveTrainerService.getSportiveTrainers()
      .subscribe(
        sportiveTrainers => this.sportiveTrainers = sportiveTrainers,
        error => this.errorMessage = <any>error
      );
  }

  onSelect(sportiveTrainer: SportiveTrainer): void {
    this.selectedSportiveTrainer = sportiveTrainer;
  }

  gotoDetail(): void {
    this.router.navigate(['/sportivesTrainers/detail', this.selectedSportiveTrainer.id]);
  }


  increasePageNo() {
    this.acceptableSize=Math.ceil(this.totalSize/this.sportiveTrainerService.getPageSize().valueOf());
    if (this.currentPage<this.acceptableSize-1){
      this.currentPage++;
      document.getElementById("decrease-btn").hidden=false;
      this.getSportiveTrainersPaginated();
    }
  }

  decreasePageNo() {
    if(this.currentPage>0) {
      this.currentPage--;
      document.getElementById("increase-btn").hidden=false;
      this.getSportiveTrainersPaginated();
    }
  }
}
