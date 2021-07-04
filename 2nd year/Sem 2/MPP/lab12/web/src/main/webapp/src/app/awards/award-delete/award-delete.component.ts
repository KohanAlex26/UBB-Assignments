import { Component, OnInit } from '@angular/core';
import {AwardsService} from "../shared/awards.service";
import {Location} from "@angular/common";
@Component({
  selector: 'app-award-delete',
  templateUrl: './awards-delete.component.html',
  styleUrls: ['./awards-delete.component.css']
})
export class AwardDeleteComponent implements OnInit {

  constructor(private awardService: AwardsService,
              private location: Location
  ) {
  }

  ngOnInit(): void {
  }


  Number(value: string) {
    return Number(value);
  }

  removeAward(id: number) {
    console.log("deleting award ", id);

    this.awardService.deleteAward(id)
      .subscribe(award => console.log("deleted award: ", award));

    this.location.back(); // ...
  }
}

