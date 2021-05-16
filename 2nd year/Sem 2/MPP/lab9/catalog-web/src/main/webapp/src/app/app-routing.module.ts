import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {SportivesComponent} from "./sportives/sportives.component";
import {SportiveNewComponent} from "./sportives/sportive-new/sportive-new.component";
import {SportiveListComponent} from "./sportives/sportive-list/sportive-list.component";


const routes: Routes = [

  {path: 'sportives', component: SportivesComponent},
  {path: 'sportive-new', component: SportiveNewComponent},

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
