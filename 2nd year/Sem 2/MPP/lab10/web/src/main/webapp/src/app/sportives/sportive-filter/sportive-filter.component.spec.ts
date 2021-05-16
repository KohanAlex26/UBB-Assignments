import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SportiveFilterComponent } from './sportive-filter.component';

describe('SportiveFilterComponent', () => {
  let component: SportiveFilterComponent;
  let fixture: ComponentFixture<SportiveFilterComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SportiveFilterComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SportiveFilterComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
