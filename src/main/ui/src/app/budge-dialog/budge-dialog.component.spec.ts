import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { BudgeDialogComponent } from './budge-dialog.component';

describe('BudgeDialogComponent', () => {
  let component: BudgeDialogComponent;
  let fixture: ComponentFixture<BudgeDialogComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ BudgeDialogComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(BudgeDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
