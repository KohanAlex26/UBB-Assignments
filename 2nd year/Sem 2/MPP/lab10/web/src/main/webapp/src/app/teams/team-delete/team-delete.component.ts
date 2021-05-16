import { Component, OnInit } from '@angular/core';
import {TeamsService} from "../shared/teams.service";
import {Location} from "@angular/common";
@Component({
  selector: 'app-team-delete',
  templateUrl: './teams-delete.component.html',
  styleUrls: ['./teams-delete.component.css']
})
export class TeamDeleteComponent implements OnInit {

  constructor(private teamService: TeamsService,
              private location: Location
  ) {
  }

  ngOnInit(): void {
  }


  Number(value: string) {
    return Number(value);
  }

  removeTeam(id: number) {
    console.log("deleting team ", id);

    this.teamService.deleteTeam(id)
      .subscribe(team => console.log("deleted team: ", team));

    this.location.back(); // ...
  }
}

