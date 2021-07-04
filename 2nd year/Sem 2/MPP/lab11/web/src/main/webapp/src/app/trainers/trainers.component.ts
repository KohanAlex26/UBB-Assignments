import {Component} from "@angular/core";
import {TrainersService} from "./shared/trainers.service";
import {Router} from "@angular/router";

@Component({
  moduleId: module.id,
  selector: 'ubb-trainers',
  templateUrl: './trainers.component.html',
  styleUrls: ['./trainers.component.css'],
})
export class TrainersComponent {
  constructor(private router: Router) {
  }

  addNewTrainer() {
    console.log("add new trainer btn clicked ");

    this.router.navigate(["trainer/new"]);
  }

  deleteTrainer() {
    console.log("delete trainer btn clicked");

    this.router.navigate(["trainer/delete"]);
  }

  updateTrainer() {
    console.log("update trainer btn clicked");

    this.router.navigate(["trainer/update"]);
  }

  filterTrainers() {
    console.log("filter trainers btn clicked");

    this.router.navigate(["trainer/filter"]);
  }

  sortTrainers() {
    console.log("sort trainers btn clicked");

    this.router.navigate(["trainer/sort"]);

  }
}
