import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ChatSellerComponent } from './chat-seller.component';

describe('ChatSellerComponent', () => {
  let component: ChatSellerComponent;
  let fixture: ComponentFixture<ChatSellerComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ChatSellerComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ChatSellerComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
