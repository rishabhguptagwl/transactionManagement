import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { MonthExpenseComponent } from './month-expense.component';

describe('MonthExpenseComponent', () => {
  let component: MonthExpenseComponent;
  let fixture: ComponentFixture<MonthExpenseComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ MonthExpenseComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(MonthExpenseComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
