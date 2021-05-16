import {Component, OnInit} from '@angular/core';
import {SportivesService} from "../shared/sportives.service";
import {Location} from "@angular/common";

@Component({
  selector: 'app-sportive-new',
  templateUrl: './sportive-new.component.html',
  styleUrls: ['./sportive-new.component.css']
})
export class SportiveNewComponent implements OnInit {

  constructor(private sportiveService: SportivesService,
              private location: Location
  ) {
  }

  ngOnInit(): void {
  }

  saveSportive(id:string, firstName: string, lastName: string, age: string, tid:string) {
    if (id=="" || firstName=="" || lastName=="" || age==""|| tid==""){
      console.log("null credentials");
      return;
    }
    var regex = /[a-z]/g;
    if (!regex.test(lastName)){
      console.log("lastName doesn't match the regex");
      return;
    }
    console.log("saving sportive", firstName, lastName, age, tid);

    this.sportiveService.saveSportive({
      id: Number(id),
      firstName: firstName,
      lastName: lastName,
      age: +age,
      tid: +tid,
    })
      .subscribe(sportive => console.log("saved sportive: ", sportive));

    this.location.back(); // ...
  }
}
