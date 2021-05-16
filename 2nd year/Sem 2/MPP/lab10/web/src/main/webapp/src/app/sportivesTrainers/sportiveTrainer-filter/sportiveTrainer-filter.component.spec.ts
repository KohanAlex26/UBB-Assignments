import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SportiveTrainerFilterComponent } from './sportiveTrainer-filter.component';

describe('SportiveTrainerFilterComponent', () => {
  let component: SportiveTrainerFilterComponent;
  let fixture: ComponentFixture<SportiveTrainerFilterComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SportiveTrainerFilterComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SportiveTrainerFilterComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
