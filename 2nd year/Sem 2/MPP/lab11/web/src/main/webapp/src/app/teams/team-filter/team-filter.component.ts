import { Component, OnInit } from '@angular/core';
import {TeamsService} from "../shared/teams.service";
import {Router} from "@angular/router";
import {Team} from "../shared/teams.model";

@Component({
  selector: 'app-team-filter',
  templateUrl: './team-filter.component.html',
  styleUrls: ['./team-filter.component.css']
})
export class TeamFilterComponent implements OnInit {
  errorMessage: string;
  teams: Array<Team>;
  teamsWithAngular: Array<Team> = [];
  constructor(private teamService: TeamsService,
              private router: Router) {
  }

  ngOnInit(): void {
  }

  filterTeams(teamName: string) {
    this.teamService.filterTeams(teamName)
      .subscribe(
        teams => this.teams = teams,
        error => this.errorMessage = <any>error
      );
  }

  filterTeamsAngular(value: string) {
    this.teamService.getTeams()
      .subscribe(teams=>this.teamsWithAngular=teams.filter(team=>team.teamName.includes(value)));
  }
}
