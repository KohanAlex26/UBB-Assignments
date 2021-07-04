import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SportiveUpdateComponent } from './sportive-update.component';

describe('SportiveUpdateComponent', () => {
  let component: SportiveUpdateComponent;
  let fixture: ComponentFixture<SportiveUpdateComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SportiveUpdateComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SportiveUpdateComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
