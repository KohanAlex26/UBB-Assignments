import {Component, OnInit} from '@angular/core';
import {AwardsService} from "../shared/awards.service";
import {Location} from "@angular/common";
import {TeamsService} from "../../teams/shared/teams.service";
import {Award} from "../shared/awards.model";
import {Team} from "../../teams/shared/teams.model";

@Component({
  selector: 'app-award-new',
  templateUrl: './award-new.component.html',
  styleUrls: ['./award-new.component.css']
})
export class AwardNewComponent implements OnInit {
  selectedTeam: Team;
  errorMessage: string;

  constructor(private awardService: AwardsService,private teamService: TeamsService,
              private location: Location
  ) {
  }

  ngOnInit(): void {
  }

  saveAward(id:string, date: string) {
    if (id=="" || date==""){
      console.log("null credentials");
      return;
    }

    console.log("saving award", date);


      this.awardService.saveAward({
        id: Number(id),
        date: date
      }).subscribe(award => console.log("saved award: ", award))

    this.location.back(); // ...
  }
}
