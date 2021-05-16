import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { TrainerDeleteComponent } from './trainer-delete.component';

describe('TrainerDeleteComponent', () => {
  let component: TrainerDeleteComponent;
  let fixture: ComponentFixture<TrainerDeleteComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ TrainerDeleteComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(TrainerDeleteComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
