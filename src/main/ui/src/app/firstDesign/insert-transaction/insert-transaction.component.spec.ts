import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { InsertTransactionComponent } from './insert-transaction.component';

describe('InsertTransactionComponent', () => {
  let component: InsertTransactionComponent;
  let fixture: ComponentFixture<InsertTransactionComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ InsertTransactionComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(InsertTransactionComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
