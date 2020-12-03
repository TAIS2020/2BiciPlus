import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ComplexProductsComponent } from './complex-products.component';

describe('ComplexProductsComponent', () => {
  let component: ComplexProductsComponent;
  let fixture: ComponentFixture<ComplexProductsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ComplexProductsComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ComplexProductsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
