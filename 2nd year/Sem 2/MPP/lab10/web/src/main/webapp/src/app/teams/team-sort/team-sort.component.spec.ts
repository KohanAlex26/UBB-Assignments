import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { TeamSortComponent } from './team-sort.component';

describe('TeamSortComponent', () => {
  let component: TeamSortComponent;
  let fixture: ComponentFixture<TeamSortComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ TeamSortComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(TeamSortComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
