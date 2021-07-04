import {Component, Input, OnInit} from '@angular/core';
import {AwardsService} from "../shared/awards.service";
import {ActivatedRoute, Params} from "@angular/router";
import {Location} from '@angular/common';
import {Award} from "../shared/awards.model";

import {switchMap} from "rxjs/operators";


@Component({
  selector: 'ubb-award-detail',
  templateUrl: './award-detail.component.html',
  styleUrls: ['./award-detail.component.css'],
})

export class AwardDetailComponent implements OnInit {

  @Input() award: Award;

  constructor(private awardService: AwardsService,
              private route: ActivatedRoute,
              private location: Location) {
  }

  ngOnInit(): void {
    this.route.params
      .pipe(switchMap((params: Params) => this.awardService.getAward(+params['id'])))
      .subscribe(award => this.award = award);
  }

  goBack(): void {
    this.location.back();
  }

  save(): void {
    this.awardService.update(this.award)
      .subscribe(_ => this.goBack());
  }
}
