import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { TrainerSortComponent } from './trainer-sort.component';

describe('TrainerSortComponent', () => {
  let component: TrainerSortComponent;
  let fixture: ComponentFixture<TrainerSortComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ TrainerSortComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(TrainerSortComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
