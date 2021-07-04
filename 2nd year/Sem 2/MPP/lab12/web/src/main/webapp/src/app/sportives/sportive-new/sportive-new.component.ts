import {Component, OnInit} from '@angular/core';
import {SportivesService} from "../shared/sportives.service";
import {Location} from "@angular/common";
import {TeamsService} from "../../teams/shared/teams.service";
import {Sportive} from "../shared/sportives.model";
import {Team} from "../../teams/shared/teams.model";

@Component({
  selector: 'app-sportive-new',
  templateUrl: './sportive-new.component.html',
  styleUrls: ['./sportive-new.component.css']
})
export class SportiveNewComponent implements OnInit {
  selectedTeam: Team;
  errorMessage: string;

  constructor(private sportiveService: SportivesService,private teamService: TeamsService,
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

    // this.teamService.getTeam(Number(tid)).subscribe(s=>console.log(s))
    // this.teamService.getTeams()
    //   .subscribe(s=>console.log(s));

    // this.teamService.getTeam(Number(tid))
    //   .subscribe(
    //     team=>this.selectedTeam=team
    //     );

    this.teamService.getTeam(Number(tid)).subscribe( team =>
      this.sportiveService.saveSportive({
        id: Number(id),
        firstName: firstName,
        lastName: lastName,
        age: +age,
        team: team,
      }).subscribe(sportive => console.log("saved sportive: ", sportive))
    )



    // this.sportiveService.saveSportive({
    //   id: Number(id),
    //   firstName: firstName,
    //   lastName: lastName,
    //   age: +age,
    //   team: this.teamService.getTeam(Number(tid)).subscribe(),
    // })
    //   .subscribe(sportive => console.log("saved sportive: ", sportive));

    this.location.back(); // ...
  }
}
