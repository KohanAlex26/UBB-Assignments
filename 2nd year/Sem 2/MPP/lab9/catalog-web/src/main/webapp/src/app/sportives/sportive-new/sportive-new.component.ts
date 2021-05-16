import {Component, OnInit} from '@angular/core';
import {SportiveService} from "../shared/sportive.service";
import {Sportive} from "../shared/sportive.model";

@Component({
  selector: 'app-sportive-new',
  templateUrl: './sportive-new.component.html',
  styleUrls: ['./sportive-new.component.css']
})
export class SportiveNewComponent implements OnInit {

  constructor(private sportiveService: SportiveService) {
  }

  ngOnInit(): void {
  }

  saveSportive(firstName: string, lastName: string, age: string, tid:string, id:string) {
    console.log("gudhgapfgif", firstName, lastName, age, tid);

    // const sportive: Sportive = {id: 0, firstName, lastName, age: +age, tid: +tid, id: +id};
    const sportive: Sportive = <Sportive>{firstName, lastName, age: +age, tid: +tid, id: +id};
    this.sportiveService.saveSportive(sportive)
      .subscribe(sportive => console.log("saved sportive: ", sportive));


  }
}
