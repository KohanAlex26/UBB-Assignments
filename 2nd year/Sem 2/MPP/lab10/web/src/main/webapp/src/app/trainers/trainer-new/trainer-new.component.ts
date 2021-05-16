import {Component, OnInit} from '@angular/core';
import {TrainersService} from "../shared/trainers.service";
import {Location} from "@angular/common";

@Component({
  selector: 'app-trainer-new',
  templateUrl: './trainer-new.component.html',
  styleUrls: ['./trainer-new.component.css']
})
export class TrainerNewComponent implements OnInit {

  constructor(private trainerService: TrainersService,
              private location: Location
  ) {
  }

  ngOnInit(): void {
  }

  saveTrainer(id:string, firstName: string, lastName: string, age: string) {
    if (id=="" || firstName=="" || lastName=="" || age==""){
      console.log("null credentials");
      return;
    }
    var regex = /[a-z]/g;
    if (!regex.test(lastName)){
      console.log("lastName doesn't match the regex");
      return;
    }
    console.log("saving trainer", firstName, lastName, age);

    this.trainerService.saveTrainer({
      id: Number(id),
      firstName: firstName,
      lastName: lastName,
      age: +age,
    })
      .subscribe(trainer => console.log("saved trainer: ", trainer));

    this.location.back(); // ...
  }
}
