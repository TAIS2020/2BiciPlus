import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-chat-customer',
  templateUrl: './chat-customer.component.html',
  styleUrls: ['./chat-customer.component.scss']
})
export class ChatCustomerComponent implements OnInit {

  constructor() { }

  ngOnInit(): void {
  }

  initOnDemand() {
    throw new Error('Method not implemented.');
  }
}
