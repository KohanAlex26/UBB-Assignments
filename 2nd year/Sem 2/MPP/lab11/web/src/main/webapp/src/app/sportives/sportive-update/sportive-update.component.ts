import { Component, OnInit } from '@angular/core';
import {SportivesService} from "../shared/sportives.service";
import {Location} from "@angular/common";
import {Team} from "../../teams/shared/teams.model";
import {TeamsService} from "../../teams/shared/teams.service";

@Component({
  selector: 'app-sportive-update',
  templateUrl: './sportive-update.component.html',
  styleUrls: ['./sportive-update.component.css']
})
export class SportiveUpdateComponent implements OnInit {
  selectedTeam: Team;
  errorMessage: string;
  constructor(private sportiveService: SportivesService,private teamService: TeamsService,
              private location: Location
  ) {
  }
  ngOnInit(): void {
  }

  updateSportive(id:number, firstName: string, lastName: string, age: string, tid: string) {

    if (id<0 || firstName=="" || lastName=="" || age=="" || tid==""){
      console.log("null credentials");
      return;
    }

    // this.teamService.getTeam(Number(tid)).subscribe(s=>console.log(s))
    // this.teamService.getTeams()
    //   .subscribe(s=>console.log(s));



      this.teamService.getTeam(Number(tid)).subscribe( team =>
      this.sportiveService.update({
        id: Number(id),
        firstName: firstName,
        lastName: lastName,
        age: +age,
        team: team,
      }).subscribe(sportive => console.log("updated sportive: ", sportive))
    )
    this.location.back(); // ...
  }

  Number(value: string) {
    return Number(value);
  }
}
