"use strict";
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var core_1 = require('@angular/core');
require('rxjs/add/operator/switchMap');
var AwardDetailComponent = (function () {
    function AwardDetailComponent(awardService, route, location) {
        this.awardService = awardService;
        this.route = route;
        this.location = location;
    }
    AwardDetailComponent.prototype.ngOnInit = function () {
        var _this = this;
        this.route.params
            .switchMap(function (params) { return _this.awardService.getAward(+params['id']); })
            .subscribe(function (award) { return _this.award = award; });
    };
    AwardDetailComponent.prototype.goBack = function () {
        this.location.back();
    };
    __decorate([
        core_1.Input()
    ], AwardDetailComponent.prototype, "award", void 0);
    AwardDetailComponent = __decorate([
        core_1.Component({
            selector: 'ubb-award-detail',
            templateUrl: './award-detail.component.html',
            styleUrls: ['./award-detail.component.css'],
        })
    ], AwardDetailComponent);
    return AwardDetailComponent;
}());
exports.AwardDetailComponent = AwardDetailComponent;
