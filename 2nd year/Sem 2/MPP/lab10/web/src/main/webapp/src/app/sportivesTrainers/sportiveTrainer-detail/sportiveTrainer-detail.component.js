"use strict";
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var core_1 = require('@angular/core');
require('rxjs/add/operator/switchMap');
var SportiveTrainerDetailComponent = (function () {
    function SportiveTrainerDetailComponent(sportiveTrainerService, route, location) {
        this.sportiveTrainerService = sportiveTrainerService;
        this.route = route;
        this.location = location;
    }
    SportiveTrainerDetailComponent.prototype.ngOnInit = function () {
        var _this = this;
        this.route.params
            .switchMap(function (params) { return _this.sportiveTrainerService.getSportiveTrainer(+params['id']); })
            .subscribe(function (sportiveTrainer) { return _this.sportiveTrainer = sportiveTrainer; });
    };
    SportiveTrainerDetailComponent.prototype.goBack = function () {
        this.location.back();
    };
    __decorate([
        core_1.Input()
    ], SportiveTrainerDetailComponent.prototype, "sportiveTrainer", void 0);
    SportiveTrainerDetailComponent = __decorate([
        core_1.Component({
            selector: 'ubb-sportiveTrainer-detail',
            templateUrl: './sportiveTrainer-detail.component.html',
            styleUrls: ['./sportiveTrainer-detail.component.css'],
        })
    ], SportiveTrainerDetailComponent);
    return SportiveTrainerDetailComponent;
}());
exports.SportiveTrainerDetailComponent = SportiveTrainerDetailComponent;
