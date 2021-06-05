import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { WeekExpenseComponent } from './week-expense.component';

describe('WeekExpenseComponent', () => {
  let component: WeekExpenseComponent;
  let fixture: ComponentFixture<WeekExpenseComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ WeekExpenseComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(WeekExpenseComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
