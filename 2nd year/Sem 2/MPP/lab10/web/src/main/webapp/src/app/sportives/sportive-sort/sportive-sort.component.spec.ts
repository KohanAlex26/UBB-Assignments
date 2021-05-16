import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SportiveSortComponent } from './sportive-sort.component';

describe('SportiveSortComponent', () => {
  let component: SportiveSortComponent;
  let fixture: ComponentFixture<SportiveSortComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SportiveSortComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SportiveSortComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
