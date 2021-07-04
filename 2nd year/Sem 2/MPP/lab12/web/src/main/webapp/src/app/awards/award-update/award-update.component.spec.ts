import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AwardUpdateComponent } from './award-update.component';

describe('AwardUpdateComponent', () => {
  let component: AwardUpdateComponent;
  let fixture: ComponentFixture<AwardUpdateComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AwardUpdateComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AwardUpdateComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
