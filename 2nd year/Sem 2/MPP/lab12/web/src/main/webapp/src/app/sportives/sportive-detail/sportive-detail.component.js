"use strict";
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var core_1 = require('@angular/core');
require('rxjs/add/operator/switchMap');
var SportiveDetailComponent = (function () {
    function SportiveDetailComponent(sportiveService, route, location) {
        this.sportiveService = sportiveService;
        this.route = route;
        this.location = location;
    }
    SportiveDetailComponent.prototype.ngOnInit = function () {
        var _this = this;
        this.route.params
            .switchMap(function (params) { return _this.sportiveService.getSportive(+params['id']); })
            .subscribe(function (sportive) { return _this.sportive = sportive; });
    };
    SportiveDetailComponent.prototype.goBack = function () {
        this.location.back();
    };
    __decorate([
        core_1.Input()
    ], SportiveDetailComponent.prototype, "sportive", void 0);
    SportiveDetailComponent = __decorate([
        core_1.Component({
            selector: 'ubb-sportive-detail',
            templateUrl: './sportive-detail.component.html',
            styleUrls: ['./sportive-detail.component.css'],
        })
    ], SportiveDetailComponent);
    return SportiveDetailComponent;
}());
exports.SportiveDetailComponent = SportiveDetailComponent;
