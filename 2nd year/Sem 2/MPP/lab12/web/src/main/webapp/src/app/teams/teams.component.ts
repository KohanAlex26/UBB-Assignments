import {Component} from "@angular/core";
import {TeamsService} from "./shared/teams.service";
import {Router} from "@angular/router";

@Component({
  moduleId: module.id,
  selector: 'ubb-teams',
  templateUrl: './teams.component.html',
  styleUrls: ['./teams.component.css'],
})
export class TeamsComponent {
  constructor(private router: Router) {
  }

  addNewTeam() {
    console.log("add new team btn clicked ");

    this.router.navigate(["team/new"]);
  }

  deleteTeam() {
    console.log("delete team btn clicked");

    this.router.navigate(["team/delete"]);
  }

  updateTeam() {
    console.log("update team btn clicked");

    this.router.navigate(["team/update"]);
  }

  filterTeams() {
    console.log("filter teams btn clicked");

    this.router.navigate(["team/filter"]);
  }

  sortTeams() {
    console.log("sort teams btn clicked");

    this.router.navigate(["team/sort"]);

  }
}
