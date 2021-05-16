import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SportiveTrainerSortComponent } from './sportiveTrainer-sort.component';

describe('SportiveTrainerSortComponent', () => {
  let component: SportiveTrainerSortComponent;
  let fixture: ComponentFixture<SportiveTrainerSortComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SportiveTrainerSortComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SportiveTrainerSortComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
