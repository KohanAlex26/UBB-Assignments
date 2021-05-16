import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {SportivesComponent} from "./sportives/sportives.component";
import {SportiveDetailComponent} from "./sportives/sportive-detail/sportive-detail.component";
import {SportiveNewComponent} from "./sportives/sportive-new/sportive-new.component";
import {SportiveDeleteComponent} from "./sportives/sportive-delete/sportive-delete.component";
import {SportiveUpdateComponent} from "./sportives/sportive-update/sportive-update.component";
import {SportiveSortComponent} from "./sportives/sportive-sort/sportive-sort.component";
import {SportiveFilterComponent} from "./sportives/sportive-filter/sportive-filter.component";
import {TeamsComponent} from "./teams/teams.component";
import {TeamDetailComponent} from "./teams/team-detail/team-detail.component";
import {TeamNewComponent} from "./teams/team-new/team-new.component";
import {TeamDeleteComponent} from "./teams/team-delete/team-delete.component";
import {TeamUpdateComponent} from "./teams/team-update/team-update.component";
import {TeamSortComponent} from "./teams/team-sort/team-sort.component";
import {TeamFilterComponent} from "./teams/team-filter/team-filter.component";
import {TrainersComponent} from "./trainers/trainers.component";
import {TrainerDetailComponent} from "./trainers/trainer-detail/trainer-detail.component";
import {TrainerNewComponent} from "./trainers/trainer-new/trainer-new.component";
import {TrainerDeleteComponent} from "./trainers/trainer-delete/trainer-delete.component";
import {TrainerUpdateComponent} from "./trainers/trainer-update/trainer-update.component";
import {TrainerSortComponent} from "./trainers/trainer-sort/trainer-sort.component";
import {TrainerFilterComponent} from "./trainers/trainer-filter/trainer-filter.component";
import {SportivesTrainersComponent} from "./sportivesTrainers/sportivesTrainers.component";
import {SportiveTrainerDetailComponent} from "./sportivesTrainers/sportiveTrainer-detail/sportiveTrainer-detail.component";
import {SportiveTrainerSortComponent} from "./sportivesTrainers/sportiveTrainer-sort/sportiveTrainer-sort.component";
import {SportiveTrainerFilterComponent} from "./sportivesTrainers/sportiveTrainer-filter/sportiveTrainer-filter.component";



const routes: Routes = [
  // { path: '', redirectTo: '/home', pathMatch: 'full' },
  {path: 'sportives', component: SportivesComponent},
  {path: 'sportive/detail/:id', component: SportiveDetailComponent},
  {path: 'sportive/new', component: SportiveNewComponent},
  {path : 'sportive/delete', component: SportiveDeleteComponent},
  {path : 'sportive/update', component: SportiveUpdateComponent},
  {path: 'sportive/sort', component:SportiveSortComponent},
  {path: 'sportive/filter', component:SportiveFilterComponent},

  {path: 'teams', component: TeamsComponent},
  {path: 'team/detail/:id', component: TeamDetailComponent},
  {path: 'team/new', component: TeamNewComponent},
  {path : 'team/delete', component: TeamDeleteComponent},
  {path : 'team/update', component: TeamUpdateComponent},
  {path: 'team/sort', component:TeamSortComponent},
  {path: 'team/filter', component:TeamFilterComponent},

  {path: 'trainers', component: TrainersComponent},
  {path: 'trainer/detail/:id', component: TrainerDetailComponent},
  {path: 'trainer/new', component: TrainerNewComponent},
  {path : 'trainer/delete', component: TrainerDeleteComponent},
  {path : 'trainer/update', component: TrainerUpdateComponent},
  {path: 'trainer/sort', component:TrainerSortComponent},
  {path: 'trainer/filter', component:TrainerFilterComponent},


  {path: 'sportivesTrainers', component: SportivesTrainersComponent},
  {path: 'sportivesTrainers/detail/:id', component: SportiveTrainerDetailComponent},
  {path: 'sportivesTrainers/sort', component:SportiveTrainerSortComponent},
  {path: 'sportivesTrainers/filter', component:SportiveTrainerFilterComponent},



];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
