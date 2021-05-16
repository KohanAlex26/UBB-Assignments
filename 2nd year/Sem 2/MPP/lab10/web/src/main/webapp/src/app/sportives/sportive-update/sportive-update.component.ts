import { Component, OnInit } from '@angular/core';
import {SportivesService} from "../shared/sportives.service";
import {Location} from "@angular/common";

@Component({
  selector: 'app-sportive-update',
  templateUrl: './sportive-update.component.html',
  styleUrls: ['./sportive-update.component.css']
})
export class SportiveUpdateComponent implements OnInit {

  constructor(private sportiveService: SportivesService,
              private location: Location
  ) {
  }
  ngOnInit(): void {
  }

  updateSportive(id:number, firstName: string, lastName: string, age: number, tid: number) {
    if (id==0 || firstName=="" || lastName=="" || age==0 || tid==0){
      console.log("null credentials");
      return;
    }
    console.log("updating sportive", id, firstName, lastName, age, tid);

    this.sportiveService.update({
      id: +id,
      firstName:firstName,
      lastName:lastName,
      age: +age,
      tid: +tid
    })
      .subscribe(sportive => console.log("updated sportive: ", sportive));

    this.location.back(); // ...
  }

  Number(value: string) {
    return Number(value);
  }
}
