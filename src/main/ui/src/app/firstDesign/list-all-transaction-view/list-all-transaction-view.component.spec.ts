import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ListAllTransactionViewComponent } from './list-all-transaction-view.component';

describe('ListAllTransactionViewComponent', () => {
  let component: ListAllTransactionViewComponent;
  let fixture: ComponentFixture<ListAllTransactionViewComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ListAllTransactionViewComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ListAllTransactionViewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
