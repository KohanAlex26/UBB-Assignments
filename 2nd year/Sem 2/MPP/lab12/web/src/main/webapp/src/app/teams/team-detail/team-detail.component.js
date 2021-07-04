"use strict";
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var core_1 = require('@angular/core');
require('rxjs/add/operator/switchMap');
var TeamDetailComponent = (function () {
    function TeamDetailComponent(teamService, route, location) {
        this.teamService = teamService;
        this.route = route;
        this.location = location;
    }
    TeamDetailComponent.prototype.ngOnInit = function () {
        var _this = this;
        this.route.params
            .switchMap(function (params) { return _this.teamService.getTeam(+params['id']); })
            .subscribe(function (team) { return _this.team = team; });
    };
    TeamDetailComponent.prototype.goBack = function () {
        this.location.back();
    };
    __decorate([
        core_1.Input()
    ], TeamDetailComponent.prototype, "team", void 0);
    TeamDetailComponent = __decorate([
        core_1.Component({
            selector: 'ubb-team-detail',
            templateUrl: './team-detail.component.html',
            styleUrls: ['./team-detail.component.css'],
        })
    ], TeamDetailComponent);
    return TeamDetailComponent;
}());
exports.TeamDetailComponent = TeamDetailComponent;
