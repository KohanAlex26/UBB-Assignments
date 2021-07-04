import {Component} from "@angular/core";
import {AwardsService} from "./shared/awards.service";
import {Router} from "@angular/router";

@Component({
  moduleId: module.id,
  selector: 'ubb-awards',
  templateUrl: './awards.component.html',
  styleUrls: ['./awards.component.css'],
})
export class AwardsComponent {
  constructor(private router: Router) {
  }

  addNewAward() {
    console.log("add new award btn clicked ");

    this.router.navigate(["award/new"]);
  }

  deleteAward() {
    console.log("delete award btn clicked");

    this.router.navigate(["award/delete"]);
  }

  updateAward() {
    console.log("update award btn clicked");

    this.router.navigate(["award/update"]);
  }

  filterAwards() {
    console.log("filter awards btn clicked");

    this.router.navigate(["award/filter"]);
  }

  sortAwards() {
    console.log("sort awards btn clicked");

    this.router.navigate(["award/sort"]);

  }
}
