import {Component, OnInit} from '@angular/core';
import {Sportive} from "../shared/sportive.model";
import {SportiveService} from "../shared/sportive.service";

@Component({
  selector: 'app-sportive-list',
  templateUrl: './sportive-list.component.html',
  styleUrls: ['./sportive-list.component.css']
})
export class SportiveListComponent implements OnInit {
  sportives: Sportive[];

  constructor(private sportiveService: SportiveService) {
  }

  ngOnInit(): void {
    this.sportiveService.getSportives()
      .subscribe(sportives =>{ this.sportives = sportives
      console.log(JSON.parse(JSON.stringify(this.sportives)))
      });



  }

}
