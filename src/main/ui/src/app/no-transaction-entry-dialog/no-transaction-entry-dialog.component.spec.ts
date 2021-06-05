import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { NoTransactionEntryDialogComponent } from './no-transaction-entry-dialog.component';

describe('NoTransactionEntryDialogComponent', () => {
  let component: NoTransactionEntryDialogComponent;
  let fixture: ComponentFixture<NoTransactionEntryDialogComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ NoTransactionEntryDialogComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(NoTransactionEntryDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
