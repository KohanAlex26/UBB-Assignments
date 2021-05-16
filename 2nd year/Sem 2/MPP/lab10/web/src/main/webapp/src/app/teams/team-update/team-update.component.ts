import { Component, OnInit } from '@angular/core';
import {TeamsService} from "../shared/teams.service";
import {Location} from "@angular/common";

@Component({
  selector: 'app-team-update',
  templateUrl: './team-update.component.html',
  styleUrls: ['./team-update.component.css']
})
export class TeamUpdateComponent implements OnInit {

  constructor(private teamService: TeamsService,
              private location: Location
  ) {
  }
  ngOnInit(): void {
  }

  updateTeam(id:number, teamName: string) {
    if (id==0 || teamName==""){
      console.log("null credentials");
      return;
    }
    console.log("updating team", id, teamName);

    this.teamService.update({
      id: +id,
      teamName:teamName,
    })
      .subscribe(team => console.log("updated team: ", team));

    this.location.back(); // ...
  }

  Number(value: string) {
    return Number(value);
  }
}
