import { Component, OnInit } from '@angular/core';
import {AwardsService} from "../shared/awards.service";
import {Location} from "@angular/common";
import {Team} from "../../teams/shared/teams.model";
import {TeamsService} from "../../teams/shared/teams.service";

@Component({
  selector: 'app-award-update',
  templateUrl: './award-update.component.html',
  styleUrls: ['./award-update.component.css']
})
export class AwardUpdateComponent implements OnInit {
  selectedTeam: Team;
  errorMessage: string;
  constructor(private awardService: AwardsService,private teamService: TeamsService,
              private location: Location
  ) {
  }
  ngOnInit(): void {
  }

  updateAward(id:number, date: string) {
    // this.teamService.getTeam(Number(tid))
    //   .subscribe(
    //     awards=>this.selectedTeam=awards,
    //     error=>this.errorMessage=<any>error);
    if (id<0 || date==""){
      console.log("null credentials");
      return;
    }
    console.log("updating award", id, date);

    this.awardService.update({
      id: +id,
      date:date
    })
      .subscribe(award => console.log("updated award: ", award));

    this.location.back(); // ...
  }

  Number(value: string) {
    return Number(value);
  }
}
