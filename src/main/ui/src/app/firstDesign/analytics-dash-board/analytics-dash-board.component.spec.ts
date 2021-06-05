import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AnalyticsDashBoardComponent } from './analytics-dash-board.component';

describe('AnalyticsDashBoardComponent', () => {
  let component: AnalyticsDashBoardComponent;
  let fixture: ComponentFixture<AnalyticsDashBoardComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AnalyticsDashBoardComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AnalyticsDashBoardComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
