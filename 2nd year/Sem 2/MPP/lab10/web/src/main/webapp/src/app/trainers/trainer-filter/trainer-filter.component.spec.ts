import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { TrainerFilterComponent } from './trainer-filter.component';

describe('TrainerFilterComponent', () => {
  let component: TrainerFilterComponent;
  let fixture: ComponentFixture<TrainerFilterComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ TrainerFilterComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(TrainerFilterComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
