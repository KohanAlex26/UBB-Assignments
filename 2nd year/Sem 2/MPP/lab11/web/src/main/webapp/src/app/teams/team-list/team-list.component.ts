import {Component, OnInit} from '@angular/core';
import {Team} from "../shared/teams.model";
import {TeamsService} from "../shared/teams.service";
import {Router} from "@angular/router";
import {Observable} from "rxjs";


@Component({
  moduleId: module.id,
  selector: 'ubb-team-list',
  templateUrl: './team-list.component.html',
  styleUrls: ['./team-list.component.css'],
})
export class TeamListComponent implements OnInit {
  errorMessage: string;
  teams: Array<Team>;
  selectedTeam: Team;
  currentPage: number;
  totalSize:number;
  acceptableSize:number;

  constructor(private teamService: TeamsService,
              private router: Router) {

  }

  ngOnInit(): void {
    this.currentPage=0;
    this.teamService.getTeams()
      .subscribe(
        teams=>this.totalSize=teams.length,
        error=>this.errorMessage=<any>error);
    this.getTeamsPaginated();
  }


  buttonsInteraction(){
    this.acceptableSize=Math.ceil(this.totalSize/this.teamService.getPageSize().valueOf());
    if (this.currentPage==0)
      document.getElementById("decrease-btn").hidden=true;
    else if (this.currentPage==this.acceptableSize-1)
      document.getElementById("increase-btn").hidden=true;
  }


  getTeamsPaginated(){
    this.buttonsInteraction();
    this.teamService.getTeamsOnPage(this.currentPage)
      .subscribe(teams=>this.teams = teams,
                error=>this.errorMessage=<any>error);
  }

  getTeams() {
    this.teamService.getTeams()
      .subscribe(
        teams => this.teams = teams,
        error => this.errorMessage = <any>error
      );
  }

  onSelect(team: Team): void {
    this.selectedTeam = team;
  }

  gotoDetail(): void {
    this.router.navigate(['/team/detail', this.selectedTeam.id]);
  }

  deleteTeam(team: Team) {
    console.log("deleting team: ", team);

    this.teamService.deleteTeam(team.id)
      .subscribe(_ => {
        console.log("team deleted");

        this.teams = this.teams
          .filter(s => s.id !== team.id);
      });
  }

  increasePageNo() {
    this.acceptableSize=Math.ceil(this.totalSize/this.teamService.getPageSize().valueOf());
    if (this.currentPage<this.acceptableSize-1){
      this.currentPage++;
      document.getElementById("decrease-btn").hidden=false;
      this.getTeamsPaginated();
    }
  }

  decreasePageNo() {
    if(this.currentPage>0) {
      this.currentPage--;
      document.getElementById("increase-btn").hidden=false;
      this.getTeamsPaginated();
    }
  }
}
