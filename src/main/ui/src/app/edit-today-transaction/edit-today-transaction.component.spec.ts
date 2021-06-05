import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { EditTodayTransactionComponent } from './edit-today-transaction.component';

describe('EditTodayTransactionComponent', () => {
  let component: EditTodayTransactionComponent;
  let fixture: ComponentFixture<EditTodayTransactionComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ EditTodayTransactionComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(EditTodayTransactionComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
