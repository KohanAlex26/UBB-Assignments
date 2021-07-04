import {Component, OnInit} from '@angular/core';
import {Sportive} from "../shared/sportives.model";
import {SportivesService} from "../shared/sportives.service";
import {Router} from "@angular/router";
import {Observable} from "rxjs";


@Component({
  moduleId: module.id,
  selector: 'ubb-sportive-list',
  templateUrl: './sportive-list.component.html',
  styleUrls: ['./sportive-list.component.css'],
})
export class SportiveListComponent implements OnInit {
  errorMessage: string;
  sportives: Array<Sportive>;
  selectedSportive: Sportive;
  currentPage: number;
  totalSize:number;
  acceptableSize:number;

  constructor(private sportiveService: SportivesService,
              private router: Router) {

  }

  ngOnInit(): void {
    this.currentPage=0;
    this.sportiveService.getSportives()
      .subscribe(
        sportives=>this.totalSize=sportives.length,
        error=>this.errorMessage=<any>error);
    this.getSportivesPaginated();
  }


  buttonsInteraction(){
    this.acceptableSize=Math.ceil(this.totalSize/this.sportiveService.getPageSize().valueOf());
    if (this.currentPage==0)
      document.getElementById("decrease-btn").hidden=true;
    else if (this.currentPage==this.acceptableSize-1)
      document.getElementById("increase-btn").hidden=true;
  }


  getSportivesPaginated(){
    this.buttonsInteraction();
    this.sportiveService.getSportivesOnPage(this.currentPage)
      .subscribe(sportives=>this.sportives = sportives,
                error=>this.errorMessage=<any>error);
  }

  getSportives() {
    this.sportiveService.getSportives()
      .subscribe(
        sportives => this.sportives = sportives,
        error => this.errorMessage = <any>error
      );
  }

  onSelect(sportive: Sportive): void {
    this.selectedSportive = sportive;
  }

  gotoDetail(): void {
    this.router.navigate(['/sportive/detail', this.selectedSportive.id]);
  }

  deleteSportive(sportive: Sportive) {
    console.log("deleting sportive: ", sportive);

    this.sportiveService.deleteSportive(sportive.id)
      .subscribe(_ => {
        console.log("sportive deleted");

        this.sportives = this.sportives
          .filter(s => s.id !== sportive.id);
      });
  }

  increasePageNo() {
    this.acceptableSize=Math.ceil(this.totalSize/this.sportiveService.getPageSize().valueOf());
    if (this.currentPage<this.acceptableSize-1){
      this.currentPage++;
      document.getElementById("decrease-btn").hidden=false;
      this.getSportivesPaginated();
    }
  }

  decreasePageNo() {
    if(this.currentPage>0) {
      this.currentPage--;
      document.getElementById("increase-btn").hidden=false;
      this.getSportivesPaginated();
    }
  }
}
