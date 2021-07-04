import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SportiveDeleteComponent } from './sportive-delete.component';

describe('SportiveDeleteComponent', () => {
  let component: SportiveDeleteComponent;
  let fixture: ComponentFixture<SportiveDeleteComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SportiveDeleteComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SportiveDeleteComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
