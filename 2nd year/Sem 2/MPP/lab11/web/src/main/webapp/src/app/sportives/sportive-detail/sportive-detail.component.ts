import {Component, Input, OnInit} from '@angular/core';
import {SportivesService} from "../shared/sportives.service";
import {ActivatedRoute, Params} from "@angular/router";
import {Location} from '@angular/common';
import {Sportive} from "../shared/sportives.model";

import {switchMap} from "rxjs/operators";


@Component({
  selector: 'ubb-sportive-detail',
  templateUrl: './sportive-detail.component.html',
  styleUrls: ['./sportive-detail.component.css'],
})

export class SportiveDetailComponent implements OnInit {

  @Input() sportive: Sportive;

  constructor(private sportiveService: SportivesService,
              private route: ActivatedRoute,
              private location: Location) {
  }

  ngOnInit(): void {
    this.route.params
      .pipe(switchMap((params: Params) => this.sportiveService.getSportive(+params['id'])))
      .subscribe(sportive => this.sportive = sportive);
  }

  goBack(): void {
    this.location.back();
  }

  save(): void {
    this.sportiveService.update(this.sportive)
      .subscribe(_ => this.goBack());
  }
}
