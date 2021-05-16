import {Component} from "@angular/core";
import {SportivesService} from "./shared/sportives.service";
import {Router} from "@angular/router";

@Component({
  moduleId: module.id,
  selector: 'ubb-sportives',
  templateUrl: './sportives.component.html',
  styleUrls: ['./sportives.component.css'],
})
export class SportivesComponent {
  constructor(private router: Router) {
  }

  addNewSportive() {
    console.log("add new sportive btn clicked ");

    this.router.navigate(["sportive/new"]);
  }

  deleteSportive() {
    console.log("delete sportive btn clicked");

    this.router.navigate(["sportive/delete"]);
  }

  updateSportive() {
    console.log("update sportive btn clicked");

    this.router.navigate(["sportive/update"]);
  }

  filterSportives() {
    console.log("filter sportives btn clicked");

    this.router.navigate(["sportive/filter"]);
  }

  sortSportives() {
    console.log("sort sportives btn clicked");

    this.router.navigate(["sportive/sort"]);

  }
}
