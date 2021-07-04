import {Component} from "@angular/core";
import {SportiveTrainerService} from "./shared/sportiveTrainer.service";
import {Router} from "@angular/router";

@Component({
  moduleId: module.id,
  selector: 'ubb-sportiveTrainers',
  templateUrl: './sportiveTrainer.component.html',
  styleUrls: ['./sportiveTrainer.component.css'],
})
export class SportiveTrainerComponent {
  constructor(private router: Router) {
  }

  addNewSportiveTrainer() {
    console.log("add new sportiveTrainer btn clicked ");

    this.router.navigate(["sportiveTrainer/new"]);
  }
  //
  // deleteSportiveTrainer() {
  //   console.log("delete sportiveTrainer btn clicked");
  //
  //   this.router.navigate(["sportiveTrainer/delete"]);
  // }
  //
  // updateSportiveTrainer() {
  //   console.log("update sportiveTrainer btn clicked");
  //
  //   this.router.navigate(["sportiveTrainer/update"]);
  // }
  //
  // filterSportiveTrainers() {
  //   console.log("filter sportiveTrainers btn clicked");
  //
  //   this.router.navigate(["sportiveTrainer/filter"]);
  // }
  //
  // sortSportiveTrainers() {
  //   console.log("sort sportiveTrainers btn clicked");
  //
  //   this.router.navigate(["sportiveTrainer/sort"]);
  //
  // }
}
