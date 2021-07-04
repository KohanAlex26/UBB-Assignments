import {Component, Input, OnInit} from '@angular/core';
import {TeamsService} from "../shared/teams.service";
import {ActivatedRoute, Params} from "@angular/router";
import {Location} from '@angular/common';
import {Team} from "../shared/teams.model";

import {switchMap} from "rxjs/operators";


@Component({
  selector: 'ubb-team-detail',
  templateUrl: './team-detail.component.html',
  styleUrls: ['./team-detail.component.css'],
})

export class TeamDetailComponent implements OnInit {

  @Input() team: Team;

  constructor(private teamService: TeamsService,
              private route: ActivatedRoute,
              private location: Location) {
  }

  ngOnInit(): void {
    this.route.params
      .pipe(switchMap((params: Params) => this.teamService.getTeam(+params['id'])))
      .subscribe(team => this.team = team);
  }

  goBack(): void {
    this.location.back();
  }

  save(): void {
    this.teamService.update(this.team)
      .subscribe(_ => this.goBack());
  }
}
