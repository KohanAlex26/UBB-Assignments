import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';
import {HttpClientModule} from '@angular/common/http';
import {FormsModule} from '@angular/forms';

import {AppComponent} from './app.component';
import {AppRoutingModule} from "./app-routing.module";
import {SportivesComponent} from './sportives/sportives.component';
import {SportiveListComponent} from './sportives/sportive-list/sportive-list.component';
import {SportiveService} from "./sportives/shared/sportive.service";
import { SportiveNewComponent } from './sportives/sportive-new/sportive-new.component';


@NgModule({
  declarations: [
    AppComponent,
    SportivesComponent,
    SportiveListComponent,
    SportiveNewComponent,


  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpClientModule,
    AppRoutingModule,
  ],
  providers: [SportiveService],
  bootstrap: [AppComponent]
})
export class AppModule {
}
