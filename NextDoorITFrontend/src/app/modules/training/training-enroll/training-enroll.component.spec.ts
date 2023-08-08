import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TrainingEnrollComponent } from './training-enroll.component';

describe('TrainingEnrollComponent', () => {
  let component: TrainingEnrollComponent;
  let fixture: ComponentFixture<TrainingEnrollComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [TrainingEnrollComponent]
    })
      .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(TrainingEnrollComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
