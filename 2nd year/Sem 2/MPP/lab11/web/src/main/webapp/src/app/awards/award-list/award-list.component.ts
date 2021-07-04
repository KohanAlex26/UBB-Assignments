import {Component, OnInit} from '@angular/core';
import {Award} from "../shared/awards.model";
import {AwardsService} from "../shared/awards.service";
import {Router} from "@angular/router";
import {Observable} from "rxjs";


@Component({
  moduleId: module.id,
  selector: 'ubb-award-list',
  templateUrl: './award-list.component.html',
  styleUrls: ['./award-list.component.css'],
})
export class AwardListComponent implements OnInit {
  errorMessage: string;
  awards: Array<Award>;
  selectedAward: Award;
  currentPage: number;
  totalSize:number;
  acceptableSize:number;

  constructor(private awardService: AwardsService,
              private router: Router) {

  }

  ngOnInit(): void {
    this.currentPage=0;
    this.awardService.getAwards()
      .subscribe(
        awards=>this.totalSize=awards.length,
        error=>this.errorMessage=<any>error);
    this.getAwardsPaginated();
  }


  buttonsInteraction(){
    this.acceptableSize=Math.ceil(this.totalSize/this.awardService.getPageSize().valueOf());
    if (this.currentPage==0)
      document.getElementById("decrease-btn").hidden=true;
    else if (this.currentPage==this.acceptableSize-1)
      document.getElementById("increase-btn").hidden=true;
  }


  getAwardsPaginated(){
    this.buttonsInteraction();
    this.awardService.getAwardsOnPage(this.currentPage)
      .subscribe(awards=>this.awards = awards,
                error=>this.errorMessage=<any>error);
  }

  getAwards() {
    this.awardService.getAwards()
      .subscribe(
        awards => this.awards = awards,
        error => this.errorMessage = <any>error
      );
  }

  onSelect(award: Award): void {
    this.selectedAward = award;
  }

  gotoDetail(): void {
    this.router.navigate(['/award/detail', this.selectedAward.id]);
  }

  deleteAward(award: Award) {
    console.log("deleting award: ", award);

    this.awardService.deleteAward(award.id)
      .subscribe(_ => {
        console.log("award deleted");

        this.awards = this.awards
          .filter(s => s.id !== award.id);
      });
  }

  increasePageNo() {
    this.acceptableSize=Math.ceil(this.totalSize/this.awardService.getPageSize().valueOf());
    if (this.currentPage<this.acceptableSize-1){
      this.currentPage++;
      document.getElementById("decrease-btn").hidden=false;
      this.getAwardsPaginated();
    }
  }

  decreasePageNo() {
    if(this.currentPage>0) {
      this.currentPage--;
      document.getElementById("increase-btn").hidden=false;
      this.getAwardsPaginated();
    }
  }
}
