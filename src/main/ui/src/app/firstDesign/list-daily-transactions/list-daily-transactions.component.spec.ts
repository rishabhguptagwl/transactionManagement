import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ListDailyTransactionsComponent } from './list-daily-transactions.component';

describe('ListDailyTransactionsComponent', () => {
  let component: ListDailyTransactionsComponent;
  let fixture: ComponentFixture<ListDailyTransactionsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ListDailyTransactionsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ListDailyTransactionsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
