import {Component} from "@angular/core";
import {SportiveTrainersService} from "./shared/sportivesTrainers.service";
import {Router} from "@angular/router";

@Component({
  moduleId: module.id,
  selector: 'ubb-sportivesTrainers',
  templateUrl: './sportivesTrainers.component.html',
  styleUrls: ['./sportivesTrainers.component.css'],
})
export class SportivesTrainersComponent {
  constructor(private router: Router) {
  }

  filterSportivesTrainers() {
    console.log("filter sportivesTrainers btn clicked");
 
    this.router.navigate(["sportivesTrainers/filter"]);
  }

  sortSportivesTrainers() {
    console.log("sort sportivesTrainers btn clicked");

    this.router.navigate(["sportivesTrainers/sort"]);

  }
}
