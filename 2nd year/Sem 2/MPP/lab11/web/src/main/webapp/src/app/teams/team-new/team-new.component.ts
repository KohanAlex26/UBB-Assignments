import {Component, OnInit} from '@angular/core';
import {TeamsService} from "../shared/teams.service";
import {Location} from "@angular/common";

@Component({
  selector: 'app-team-new',
  templateUrl: './team-new.component.html',
  styleUrls: ['./team-new.component.css']
})
export class TeamNewComponent implements OnInit {

  constructor(private teamService: TeamsService,
              private location: Location
  ) {
  }

  ngOnInit(): void {
  }

  saveTeam(id:string, teamName: string) {
    if (id=="" || teamName==""){
      console.log("null credentials");
      return;
    }
    var regex = /[a-z]/g;
    if (!regex.test(teamName)){
      console.log("teamName doesn't match the regex");
      return;
    }
    console.log("saving team", teamName);

    this.teamService.saveTeam({
      id: Number(id),
      teamName: teamName,
    })
      .subscribe(team => console.log("saved team: ", team));

    this.location.back(); // ...
  }
}
