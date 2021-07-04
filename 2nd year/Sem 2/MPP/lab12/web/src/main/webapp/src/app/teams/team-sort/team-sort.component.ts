import { Component, OnInit } from '@angular/core';
import {Team} from "../shared/teams.model";
import {TeamsService} from "../shared/teams.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-team-sort',
  templateUrl: './team-sort.component.html',
  styleUrls: ['./team-sort.component.css']
})
export class TeamSortComponent implements OnInit {
  errorMessage: string;
  teams: Array<Team>;

  constructor(private teamService: TeamsService,
              private router: Router) {
    this.teamService.sortTeams()
      .subscribe(
        teams => this.teams = teams,
        error => this.errorMessage = <any>error
      );
  }

  ngOnInit(): void {
  }

}
