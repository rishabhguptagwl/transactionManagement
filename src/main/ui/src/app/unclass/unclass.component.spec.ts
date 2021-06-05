import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { UnclassComponent } from './unclass.component';

describe('UnclassComponent', () => {
  let component: UnclassComponent;
  let fixture: ComponentFixture<UnclassComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ UnclassComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(UnclassComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
