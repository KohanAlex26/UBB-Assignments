import { Component, OnInit } from '@angular/core';
import {SportivesService} from "../shared/sportives.service";
import {Location} from "@angular/common";
@Component({
  selector: 'app-sportive-delete',
  templateUrl: './sportives-delete.component.html',
  styleUrls: ['./sportives-delete.component.css']
})
export class SportiveDeleteComponent implements OnInit {

  constructor(private sportiveService: SportivesService,
              private location: Location
  ) {
  }

  ngOnInit(): void {
  }


  Number(value: string) {
    return Number(value);
  }

  removeSportive(id: number) {
    console.log("deleting sportive ", id);

    this.sportiveService.deleteSportive(id)
      .subscribe(sportive => console.log("deleted sportive: ", sportive));

    this.location.back(); // ...
  }
}

