import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';
import {HttpClientModule} from '@angular/common/http';
import {FormsModule} from '@angular/forms';

import {AppComponent} from './app.component';
import {AppRoutingModule} from "./app-routing.module";

import {SportivesService} from "./sportives/shared/sportives.service";
import {SportiveDetailComponent} from "./sportives/sportive-detail/sportive-detail.component";
import {SportivesComponent} from "./sportives/sportives.component";
import {SportiveListComponent} from "./sportives/sportive-list/sportive-list.component";
import { SportiveNewComponent } from './sportives/sportive-new/sportive-new.component';
import { SportiveDeleteComponent } from './sportives/sportive-delete/sportive-delete.component';
import { SportiveUpdateComponent } from './sportives/sportive-update/sportive-update.component';
import { SportiveFilterComponent } from './sportives/sportive-filter/sportive-filter.component';
import { SportiveSortComponent } from './sportives/sportive-sort/sportive-sort.component';


import {TeamsService} from "./teams/shared/teams.service";
import {TeamSortComponent} from "./teams/team-sort/team-sort.component";
import {TeamFilterComponent} from "./teams/team-filter/team-filter.component";
import {TeamUpdateComponent} from "./teams/team-update/team-update.component";
import {TeamNewComponent} from "./teams/team-new/team-new.component";
import {TeamsComponent} from "./teams/teams.component";
import {TeamDetailComponent} from "./teams/team-detail/team-detail.component";
import {TeamListComponent} from "./teams/team-list/team-list.component";
import {TeamDeleteComponent} from "./teams/team-delete/team-delete.component";


import {TrainersService} from "./trainers/shared/trainers.service";
import {TrainerDetailComponent} from "./trainers/trainer-detail/trainer-detail.component";
import {TrainersComponent} from "./trainers/trainers.component";
import {TrainerListComponent} from "./trainers/trainer-list/trainer-list.component";
import {TrainerNewComponent} from "./trainers/trainer-new/trainer-new.component";
import {TrainerUpdateComponent} from "./trainers/trainer-update/trainer-update.component";
import {TrainerFilterComponent} from "./trainers/trainer-filter/trainer-filter.component";
import {TrainerSortComponent} from "./trainers/trainer-sort/trainer-sort.component";
import {TrainerDeleteComponent} from "./trainers/trainer-delete/trainer-delete.component";

// import {SportiveTrainersService} from "./sportivesTrainers/shared/sportivesTrainers.service";
// import {SportiveTrainerDetailComponent} from "./sportivesTrainers/sportiveTrainer-detail/sportiveTrainer-detail.component";
// import {SportiveTrainerListComponent} from "./sportivesTrainers/sportiveTrainer-list/sportiveTrainer-list.component";
// import {SportiveTrainerSortComponent} from "./sportivesTrainers/sportiveTrainer-sort/sportiveTrainer-sort.component";
// import {SportiveTrainerFilterComponent} from "./sportivesTrainers/sportiveTrainer-filter/sportiveTrainer-filter.component";
// import {SportivesTrainersComponent} from "./sportivesTrainers/sportivesTrainers.component";
// import {SportiveTrainerNewComponent} from "./sportivesTrainers/sportiveTrainer-new/sportiveTrainer-new.component";


import {AwardDetailComponent} from "./awards/award-detail/award-detail.component";
import {AwardsComponent} from "./awards/awards.component";
import {AwardListComponent} from "./awards/award-list/award-list.component";
import {AwardNewComponent} from "./awards/award-new/award-new.component";
import {AwardDeleteComponent} from "./awards/award-delete/award-delete.component";
import {AwardUpdateComponent} from "./awards/award-update/award-update.component";
import {AwardsService} from "./awards/shared/awards.service";


import {SportiveTrainerNewComponent} from "./sportivesTrainers/sportiveTrainer-new/sportiveTrainer-new.component";
import {SportiveTrainerService} from "./sportivesTrainers/shared/sportiveTrainer.service";
import {SportiveTrainerComponent} from "./sportivesTrainers/sportiveTrainer.component";






@NgModule({
  declarations: [
    AppComponent,
    SportiveDetailComponent,
    SportivesComponent,
    SportiveListComponent,
    SportiveNewComponent,
    SportiveDeleteComponent,
    SportiveUpdateComponent,
    SportiveFilterComponent,
    SportiveSortComponent,

    TeamDetailComponent,
    TeamsComponent,
    TeamListComponent,
    TeamNewComponent,
    TeamDeleteComponent,
    TeamUpdateComponent,
    TeamFilterComponent,
    TeamSortComponent,

    TrainerDetailComponent,
    TrainersComponent,
    TrainerListComponent,
    TrainerNewComponent,
    TrainerDeleteComponent,
    TrainerUpdateComponent,
    TrainerFilterComponent,
    TrainerSortComponent,

    SportiveTrainerComponent,
    SportiveTrainerNewComponent,
    // SportiveTrainerDetailComponent,
    // SportivesTrainersComponent,
    // SportiveTrainerListComponent,
    // SportiveTrainerFilterComponent,
    // SportiveTrainerSortComponent,


    AwardDetailComponent,
    AwardsComponent,
    AwardListComponent,
    AwardNewComponent,
    AwardDeleteComponent,
    AwardUpdateComponent,


  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpClientModule,
    AppRoutingModule,
  ],
  providers: [SportivesService,TeamsService,TrainersService,AwardsService, SportiveTrainerService],
  bootstrap: [AppComponent]
})
export class AppModule {
}
